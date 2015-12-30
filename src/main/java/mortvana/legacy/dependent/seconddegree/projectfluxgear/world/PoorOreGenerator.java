package mortvana.legacy.dependent.seconddegree.projectfluxgear.world;

import mortvana.legacy.clean.projectfluxgear.world.PoorOreGenBase;
import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;

import mortvana.melteddashboard.util.helpers.LoadedHelper;
import mortvana.legacy.dependent.firstdegree.core.common.FluxGearContent;
import mortvana.legacy.clean.core.common.FluxGearConfigWorld;

import static mortvana.melteddashboard.common.MeltedDashboardConfig.debugWorldgen;

public class PoorOreGenerator {

	public PoorOreGenerator() {}

	public static void registerOres() {
		if (FluxGearConfigWorld.generatePoorChalcocite == 0 || (FluxGearConfigWorld.generatePoorChalcocite == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(0, MAIN));
		}
		if (FluxGearConfigWorld.generatePoorCassiterite == 0 || (FluxGearConfigWorld.generatePoorCassiterite == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(1, MAIN));
		}
		if (FluxGearConfigWorld.generatePoorGalena == 0 || (FluxGearConfigWorld.generatePoorGalena == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(2, MAIN));
		}
		if (FluxGearConfigWorld.generatePoorAcanthite == 0 || (FluxGearConfigWorld.generatePoorAcanthite == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(3, MAIN));
		}
		if (FluxGearConfigWorld.generatePoorGarnierite == 0 || (FluxGearConfigWorld.generatePoorGarnierite == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(4, MAIN));
		}
		if (FluxGearConfigWorld.generatePoorSphalerite == 0 || (FluxGearConfigWorld.generatePoorSphalerite == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(5, MAIN));
		}
		if (FluxGearConfigWorld.generatePoorBismuthinite == 0 || (FluxGearConfigWorld.generatePoorBismuthinite == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(6, MAIN));
		}
		if (FluxGearConfigWorld.generatePoorPyrolusite == 0 || (FluxGearConfigWorld.generatePoorPyrolusite == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(7, MAIN));
		}
		if (FluxGearConfigWorld.generatePoorBauxite == 0 || (FluxGearConfigWorld.generatePoorBauxite == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(8, MAIN));
		}
		if (FluxGearConfigWorld.generatePoorCooperite == 0 || (FluxGearConfigWorld.generatePoorCooperite == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(9, MAIN));
		}
		if (FluxGearConfigWorld.generatePoorBraggite == 0 || (FluxGearConfigWorld.generatePoorBraggite == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(10, MAIN));
		}
		if (FluxGearConfigWorld.generatePoorMolybdenite == 0 || (FluxGearConfigWorld.generatePoorMolybdenite == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(11, MAIN));
		}
		if (FluxGearConfigWorld.generatePoorCobaltite == 0 || (FluxGearConfigWorld.generatePoorCobaltite == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(12, MAIN));
		}
		if (FluxGearConfigWorld.generatePoorWolframite == 0 || (FluxGearConfigWorld.generatePoorWolframite == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(13, MAIN));
		}
		if (FluxGearConfigWorld.generatePoorIlmenite == 0 || (FluxGearConfigWorld.generatePoorIlmenite == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(14, MAIN));
		}
		if (FluxGearConfigWorld.generatePoorChromite == 0 || (FluxGearConfigWorld.generatePoorChromite == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(15, MAIN));
		}
		if (FluxGearConfigWorld.generatePoorCinnabar == 0 || (FluxGearConfigWorld.generatePoorCinnabar == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(16, AUX));
		}
		if (FluxGearConfigWorld.generatePoorPitchblende == 0 || (FluxGearConfigWorld.generatePoorPitchblende == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(17, AUX));
		}
		if (FluxGearConfigWorld.generatePoorMonazite == 0 || (FluxGearConfigWorld.generatePoorMonazite == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(18, AUX));
		}
		if (FluxGearConfigWorld.generatePoorNiedermayrite == 0 || (FluxGearConfigWorld.generatePoorNiedermayrite == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(19, AUX));
		}
		if (FluxGearConfigWorld.generatePoorGreenockite == 0 || (FluxGearConfigWorld.generatePoorGreenockite == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(20, AUX));
		}
		if (FluxGearConfigWorld.generatePoorGaotaiite == 0 || (FluxGearConfigWorld.generatePoorGaotaiite == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))){
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(21, AUX));
		}
		if (FluxGearConfigWorld.generatePoorOsarsite == 0 || (FluxGearConfigWorld.generatePoorOsarsite == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))){
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(22, AUX));
		}
		if (FluxGearConfigWorld.generatePoorZnamenskyite == 0 || (FluxGearConfigWorld.generatePoorZnamenskyite == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))){
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(23, AUX));
		}
		if (FluxGearConfigWorld.generatePoorGallobeudanite == 0 || (FluxGearConfigWorld.generatePoorGallobeudanite == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))){
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(24, AUX));
		}
		if (FluxGearConfigWorld.generatePoorTetrahedrite == 0 || (FluxGearConfigWorld.generatePoorTetrahedrite == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))){
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(25, AUX));
		}
		if (FluxGearConfigWorld.generatePoorTennantite == 0 || (FluxGearConfigWorld.generatePoorTennantite == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))){
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(26, AUX));
		}
		if (FluxGearConfigWorld.generatePoorSantafeite == 0 || (FluxGearConfigWorld.generatePoorSantafeite == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))){
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(27, AUX));
		}
		if (FluxGearConfigWorld.generatePoorMagnetite == 0 || (FluxGearConfigWorld.generatePoorMagnetite == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))){
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(28, AUX));
		}
		if (FluxGearConfigWorld.generatePoorDioptase == 0 || (FluxGearConfigWorld.generatePoorDioptase == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))){
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(29, AUX));
		}
		//if (FluxGearConfigWorld.generatePoorPyrope == 0 || (FluxGearConfigWorld.generatePoorPyrope == 2 && FluxGearCore.isRailcraftLoaded || debugWorldgen)){
		//    MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(30, AUX));
		//}
		if (FluxGearConfigWorld.generatePoorMyuvil == 0 || (FluxGearConfigWorld.generatePoorMyuvil == 2 && (LoadedHelper.isRailcraftLoaded || debugWorldgen))){
			MinecraftForge.ORE_GEN_BUS.register(new PoorOreGenBase(31, AUX));
		}
	}

	public static final Block MAIN = FluxGearContent.blockPoorOreMain;
	public static final Block AUX = FluxGearContent.blockPoorOreAux;
}
