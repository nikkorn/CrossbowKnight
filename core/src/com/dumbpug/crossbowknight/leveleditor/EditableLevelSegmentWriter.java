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
 * Used to write level segments to disk.
 * Created by nik on 03/10/16.
 */
public class EditableLevelSegmentWriter {
    /** The level segment to write. */
    private EditableLevelSegment segment;
    /** The segment directory. */
    private String segmentDir = "";

    /**
     * Creates a new instance of EditableLevelSegmentWriter.
     * @param segment
     */
    public EditableLevelSegmentWriter(EditableLevelSegment segment) { this.segment = segment; }

    /**
     * Write the segment to disk.
     * @param segmentName
     */
    public void writeSegment(String segmentName) {
        // Create a unique id for the segment.
        String dateTime = new SimpleDateFormat("yyyyMMdd-HHmmss").format(Calendar.getInstance().getTime());
        segmentDir = segment + "-" + dateTime;
        // Make the segment directory.
        new File(segmentDir).mkdir();
        // Write 'background' file.
        writeBackgroundFile();
        // Write 'background' file.
        writeDecorationsFile();
        // Write 'background' file.
        writeBlocksFile();
        // We have finished writing the file.
        System.out.println("Wrote level : '" + segmentDir + "'");
    }

    /**
     * Write the background file.
     */
    private void writeBackgroundFile() {
        PrintWriter backgroundFileWriter = null;
        try {
            backgroundFileWriter = new PrintWriter(new FileWriter(segmentDir + "/background"));
            JSONArray backgroundTileArray = new JSONArray();
            // Write every background tile to our JSON array.
            for(Tile tile : segment.getTiles()) {
                if(tile.getBackgroundTexture() != null) {
                    JSONObject backgroundTileObject = new JSONObject();
                    backgroundTileObject.put("x", tile.getX());
                    backgroundTileObject.put("y", tile.getY());
                    backgroundTileObject.put("typeId", tile.getBackgroundTexture().getTextureIndex());
                    backgroundTileArray.put(backgroundTileObject);
                }
            }
            // Write the JSON array to the file.
            backgroundFileWriter.write(backgroundTileArray.length() == 0 ? "[]" : backgroundTileArray.toString());
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
            decorationsFileWriter = new PrintWriter(new FileWriter(segmentDir + "/decorations"));
            JSONArray decorationTileArray = new JSONArray();
            // Write every decoration tile to our JSON array.
            for(Tile tile : segment.getTiles()) {
                if(tile.getDecorationTexture() != null) {
                    JSONObject decorationTileObject = new JSONObject();
                    decorationTileObject.put("x", tile.getX());
                    decorationTileObject.put("y", tile.getY());
                    decorationTileObject.put("typeId", tile.getDecorationTexture().getTextureIndex());
                    decorationTileArray.put(decorationTileObject);
                }
            }
            // Write the JSON array to the file.
            decorationsFileWriter.write(decorationTileArray.length() == 0 ? "[]" : decorationTileArray.toString());
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
            blocksFileWriter = new PrintWriter(new FileWriter(segmentDir + "/blocks"));
            JSONArray blockTileArray = new JSONArray();
            // Write every block tile to our JSON array.
            for(Tile tile : segment.getTiles()) {
                if(tile.getPhysicsBlock() != null) {
                    JSONObject blockTileObject = new JSONObject();
                    blockTileObject.put("x", tile.getX());
                    blockTileObject.put("y", tile.getY());
                    blockTileObject.put("typeId", tile.getPhysicsBlock().getBlockTexture().getTextureIndex());
                    blockTileObject.put("tileFillType", tile.getPhysicsBlock().getFillType().ordinal());
                    blockTileArray.put(blockTileObject);
                }
            }
            // Write the JSON array to the file.
            blocksFileWriter.write(blockTileArray.length() == 0 ? "[]" : blockTileArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        blocksFileWriter.close();
    }
    
    /**
     * Writes the connectors file.
     */
    public void writeConnectorsFile() {
    	 PrintWriter connectorsFileWriter = null;
         try {
        	 connectorsFileWriter     = new PrintWriter(new FileWriter(segmentDir + "/connectors"));
             JSONArray connectorArray = new JSONArray();
             // Write every connector tile to our JSON array.
             for(Connector connector : segment.getConnectors()) {
                 JSONObject connectorObject = new JSONObject();
                 connectorObject.put("x", connector.getTilePositionX());
                 connectorObject.put("y", connector.getTilePositionY());
                 connectorObject.put("typeId", connector.getConnecterType());
                 connectorObject.put("height", connector.getTileHeight());
                 connectorArray.put(connectorObject);
             }
             // Write the JSON array to the file.
             connectorsFileWriter.write(connectorArray.length() == 0 ? "[]" : connectorArray.toString());
         } catch (IOException e) {
             e.printStackTrace();
         }
         connectorsFileWriter.close();
    }
}
