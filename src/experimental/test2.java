package experimental;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Assert;
import org.newdawn.slick.BasicGame;

import im.bci.tmxloader.TmxLoader;
import im.bci.tmxloader.TmxMap;
import im.bci.tmxloader.TmxTile;
import im.bci.tmxloader.TmxTileset;

public abstract class test2 extends BasicGame{

    public test2(String title) {
		super(title);
	}

    protected TmxMap testFile(File mapFile) throws IOException {
    	
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
		return map;
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