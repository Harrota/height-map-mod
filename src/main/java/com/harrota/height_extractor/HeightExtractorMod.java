package com.harrota.height_extractor;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class HeightExtractorMod implements ModInitializer {
    public static final String MOD_ID = "height_extractor";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Height extractor loaded");

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(literal("height")
                    .then(argument("x1", IntegerArgumentType.integer())
                            .executes(context -> 0)
                            .then(argument("z1", IntegerArgumentType.integer())
                                    .executes(context -> 0).then(argument("x2", IntegerArgumentType.integer())
                                            .executes(context -> 0).then(argument("z2", IntegerArgumentType.integer())
                                                    .executes(context -> 0).then(argument("step", IntegerArgumentType.integer())
                                                            .executes(context -> {
                                                                final int x1 = IntegerArgumentType.getInteger(context, "x1");
                                                                final int z1 = IntegerArgumentType.getInteger(context, "z1");
                                                                final int x2 = IntegerArgumentType.getInteger(context, "x2");
                                                                final int z2 = IntegerArgumentType.getInteger(context, "z2");
                                                                final int step = IntegerArgumentType.getInteger(context, "step");
                                                                context.getSource().sendFeedback(() -> Text.literal("Calculating heights for [%s,%s] [%s,%s] with step: %s".formatted(x1, z1, x2, z2, step)), false);

                                                                BlockPos pos1 = new BlockPos(x1, 0, z1);
                                                                BlockPos pos2 = new BlockPos(x2, 0, z2);

                                                                try {
                                                                    HeightAreaCalculator heightAreaCalculator = new HeightAreaCalculator(context.getSource().getWorld(), step);
                                                                    heightAreaCalculator.calculateHeights(pos1, pos2);
                                                                } catch (IOException e) {
                                                                    throw new RuntimeException(e);
                                                                }
                                                                context.getSource().sendFeedback(() -> Text.literal("Calculation finished"), false);

                                                                return x1;
                                                            })
                                                    )
                                            )
                                    ))));
        });
    }
}