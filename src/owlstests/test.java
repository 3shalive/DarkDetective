package owlstests;

import im.bci.tmxloader.TmxLoader;
import im.bci.tmxloader.TmxMap;
import im.bci.tmxloader.TmxTile;
import im.bci.tmxloader.TmxTileset;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author devnewton
 * @author Casper Faergemand (shorttail at gmail dot com)
 */
public class test {

    @Test
    public void simpleTmx() throws IOException {
        File mapFileCSV = new File("test-data/desert.tmx");
        testFile(mapFileCSV);
        File mapFileBase64 = new File("test-data/desert_base64_uncompressed.tmx");
        testFile(mapFileBase64);
        File mapFileBase64Gzip = new File("test-data/desert_base64_gzip.tmx");
        testFile(mapFileBase64Gzip);
        File mapFileBase64Zlib = new File("test-data/desert_base64_zlib.tmx");
        testFile(mapFileBase64Zlib);
    }

    private void testFile(File mapFile) throws IOException {
        final File mapParentDir = mapFile.getParentFile().getCanonicalFile();
        TmxLoader loader = new TmxLoader();
        TmxMap map = new TmxMap();
        loader.parseTmx(map, loadText(mapFile));
        
        for (TmxTileset tileset : map.getTilesets()) {
            File tilesetParentDir;
            if (null != tileset.getSource()) {
                final File tilesetFile = new File(mapParentDir, tileset.getSource());
                tilesetParentDir = tilesetFile.getParentFile().getCanonicalFile();
                loader.parseTsx(map, tileset, loadText(tilesetFile));
            } else {
                tilesetParentDir = mapParentDir;
            }
            if (null != tileset.getImage()) {
                tileset.getImage().setSource(
                        convertRelativeToAbsolutePath(tilesetParentDir, tileset.getImage().getSource()));
            }
            for (TmxTile tile : tileset.getTiles()) {
                tile.getFrame()
                        .getImage()
                        .setSource(
                                convertRelativeToAbsolutePath(tilesetParentDir, tile.getFrame().getImage().getSource()));
            }
        }
        loader.decode(map);

        Assert.assertEquals(40, map.getWidth());
        Assert.assertEquals(40, map.getHeight());
        Assert.assertEquals(32, map.getTilewidth());
        Assert.assertEquals(32, map.getTileheight());
        Assert.assertEquals(1, map.getLayers().size());
        // Test that the map was actually decoded. Tile at 0,0 has id 29.
        Assert.assertEquals(29, map.getLayers().get(0).getTileAt(0, 0).getTile().getId());

    }

    private String convertRelativeToAbsolutePath(File parentDir, String relativePath) throws IOException {
        if (new File(relativePath).isAbsolute()) {
            return relativePath;
        }
        return new File(parentDir, relativePath).getCanonicalPath();
    }

    private String loadText(File f) throws IOException {
        try (InputStream is = new FileInputStream(f); Scanner s = new Scanner(is, "UTF-8").useDelimiter("\\Z")) {
            return s.next();
        }
    }
}