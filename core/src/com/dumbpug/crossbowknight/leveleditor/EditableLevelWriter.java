package com.dumbpug.crossbowknight.leveleditor;

import com.dumbpug.crossbowknight.tiles.Tile;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by nik on 03/10/16.
 */
public class EditableLevelWriter {
    /** The level to write. */
    private EditableLevel level;
    /** The level directory. */
    private String levelDir = "";

    /**
     * Creates a new instance of EditableLevelWriter.
     * @param level
     */
    public EditableLevelWriter(EditableLevel level) {
        this.level = level;
    }

    /**
     * Write the level to disk.
     * @param levelName
     */
    public void writeLevel(String levelName) {
        // Create a unique id for the level.
        String dateTime = new SimpleDateFormat("yyyy:MM:dd-HH:mm:ss").format(Calendar.getInstance().getTime());
        levelDir = levelName + "-" + dateTime;
        // Make the level directory.
        new File(levelDir).mkdir();
        // Write 'background' file.
        writeBackgroundFile();
        // Write 'background' file.
        writeDecorationsFile();
        // Write 'background' file.
        writeBlocksFile();
        // We have finished writing the file.
        System.out.println("Wrote level : '" + levelDir + "'");
    }

    /**
     * Write the background file.
     */
    private void writeBackgroundFile() {
        PrintWriter backgroundFileWriter = null;
        try {
            backgroundFileWriter = new PrintWriter(new FileWriter(levelDir + "/background"));
            JSONArray backgroundTileArray = new JSONArray();
            // Write every background tile to our JSON array.
            for(Tile tile : level.getLevelTiles()) {
                if(tile.getBackgroundTexture() != null) {
                    JSONObject backgroundTileObject = new JSONObject();
                    backgroundTileObject.put("x", tile.getX());
                    backgroundTileObject.put("y", tile.getY());
                    backgroundTileObject.put("typeId", tile.getBackgroundTexture().getTextureIndex());
                    backgroundTileArray.put(backgroundTileObject);
                }
            }
            // Write the JSON array to the file.
            backgroundFileWriter.write(backgroundTileArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        backgroundFileWriter.close();
    }

    /**
     * Write the decorations file.
     */
    private void writeDecorationsFile() {
        PrintWriter decorationsFileWriter = null;
        try {
            decorationsFileWriter = new PrintWriter(new FileWriter(levelDir + "/decorations"));


        } catch (IOException e) {
            e.printStackTrace();
        }
        decorationsFileWriter.close();
    }

    /**
     * Write the blocks file.
     */
    private void writeBlocksFile() {
        PrintWriter blocksFileWriter = null;
        try {
            blocksFileWriter = new PrintWriter(new FileWriter(levelDir + "/blocks"));


        } catch (IOException e) {
            e.printStackTrace();
        }
        blocksFileWriter.close();
    }
}
