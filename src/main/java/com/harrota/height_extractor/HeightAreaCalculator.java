package com.harrota.height_extractor;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;

import static com.harrota.height_extractor.HeightExtractorMod.MOD_ID;
//x +left -right
//z +forward -backward
public class HeightAreaCalculator {

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static final String CSV_FILE = "height_map.csv";
    private static final String SEPARATOR = ",";

    private final World world;
    private int step = 1;

    public HeightAreaCalculator(World world, int step) throws IOException {
        this.world = world;
        this.step = step;
    }

    public void calculateHeights(BlockPos pos1, BlockPos pos2){
        try {
            FileWriter writer = new FileWriter(CSV_FILE);
            writeHeightsToCSV(writer, pos1, pos2);
            writer.close();
            LOGGER.info("CSV File successfully written");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeHeightsToCSV(FileWriter writer, BlockPos pos1, BlockPos pos2) throws IOException {
        for (int z = pos1.getZ(); z >= pos2.getZ(); z -= step) {
            for (int x = pos1.getX(); x >= pos2.getX(); x -= step) {
                int y = getHighestY(x, z);
                writer.append(Integer.toString(y));
                writer.append(SEPARATOR);
            }
            writer.append("\n");
        }
    }

    private int getHighestY(int x, int z) {
        for (int y = 320; y != -64; y--) {
            if(!world.getBlockState(new BlockPos(x, y, z)).isAir()){
                return y;
            }
        }
        return 0;
    }

}
