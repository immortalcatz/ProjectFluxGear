package mortvana.legacy.clean;

public class Catctuse {

	//Legacy Plugins and Modules
	public enum Plugins {
		Mystcraft,
		Buildcraft,
		AE2,
		WAILA,
		NEI,
		Mek,
		MFR,
		IC3,
		Tinkers,
		TC4,
		M4Synth,
		Bees
	}

	public enum Modules {
		WeirdScience,
		TinkersDynamos,
		Tools,
		Tinkering,
		Machines,
		Dynamos,
		Core,
		Coilguns,
		Chemistry,
		Armor,
		Alloys,
		Stars,
		Solar,
		Atomic
	}

	public void dummyContent() {/*

		00,000-00,499: Ingots
		00,500-00,999: Dusts
		01,000-01,499: Nuggets
		01,500-01,999: Gems/Misc. Raw
		02,000-02,499: Trace Minerals
		02,500-02,999: 1/4 Dusts
		03,000-03,499: 1/9 Dusts
		03,500-03,999: Gears
		04,000-04,499: Foils
		04,500-04,999: Plates
		05,000-05,499: Dense Plates
		05,500-05,999: Washers
		06,000-06,499: Bolts
		06,500-06,999: Nuts
		07,000-07,499: Ball Bearings
		07,500-07,999: Shafts
		08,000-08,499: Panels
					   Large Ingots
					   Cast Ingots
					   Pile of Ingots


		10,000-10,999: Tiered Components
		11,000-11,999: Misc. Components
		====
		15,000-15,999: Thaumic Revelations
	*/}

	public void oreContent() {/*
		00,000-00,999: Ore Chips
					   Small Ore Chips
		01,000-01,999: Dirty Ore Chunks (Large)
		02,000-02,999: Dirty Ore Chunks (Medium)
		03,000-03,999: Dirty Ore Chunks (Small)
		04,000-04,999: Clean Ore Chunks (Large)
		05,000-05,999: Clean Ore Chunks (Medium)
		06,000-06,999: Clean Ore Chunks (Small)
		07,000-07,999: Crushed Ores
		08,000-08,999: Purified Crushed Ores
		09,000-09,999: Dirty Ground Ores
		10,000-10,999: Clean Ground Ores
		11,000-11,999: Impure Ore Dusts (Ore)
		12,000-12,999: Impure Ore Dusts (Alloy)
		13,000-13,999: Purified Ore Dusts (Ore)
		14,000-14,999: Purified Ore Dusts (Alloy)
		15,000-15,999: Ore Slurries
		16,000-16,999: Ore Solutions
		17,000-17,999: Ore Flakes
		18,000-18,999: Pulverized Ore Flakes
		19,000-19,999: Centrifuged Ores
		20,000-20,999: Purified Centrifuged Ores
		21,000-21,999: Rendered Ore Chunks
		22,000-22,999: Crystallized Ores
	*/}

	public void interactiveContent() {/*
		Thermite (Fire Starter; Rust + Aluminium)
		Alum (Insta-heal, crafted using Al dust)
		Bag full of Spiders
		Natura Boats [Maple, Purple, Silver, Tiger, Willow, Blood, Sakura, Redwood, Eucalyptus]
	*/}

	//General Materials (ingot, dust,   nugget)
	/*copper            (true,  true,   true)
	tin                 (true,  true,   true)
	lead                (true,  true,   true)
	silver              (true,  true,   true)
	nickel              (true,  true,   true)
	zinc                (true,  true,   true)
	bismuth             (true,  true,   true)
	manganese           (true,  true,   true)
	aluminium           (true,  true,   true)
	platinum            (true,  true,   true)
	palladium           (true,  true,   true)
	molybdenum          (true,  true,   true)
	cobalt              (true,  true,   true)
	tungsten            (true,  true,   true)
	titanium            (true,  true,   true)
	chromium            (true,  true,   true)
	arsenic             (true,  true,   true)
	antimony            (true,  true,   true)
	neodymium           (true,  true,   true)
	tesseractium        (true,  true,   true)
	cadmium             (true,  true,   true)
	tellurium           (true,  true,   true)
	osmium              (true,  true,   true)
	iridium             (true,  true,   true)
	indium              (true,  true,   true)
	antimonialBronze    (true,  true,   true)
	arsenicalBronze     (true,  true,   true)
	vanadium            (true,  true,   true)
	unobtainium         (true,  true,   true)
	dioptase            (true,  true,   true*)
	pyrope              (true,  true,   true*)
	myuvil              (false,  true,  false)
	bronze              (true,  true,   true)
	brass               (true,  true,   true)
	invar               (true,  true,   true)
	bismuthBronze       (true,  true,   true)
	cupronickel         (true,  true,   true)
	aluminiumBrass      (true,  true,   true)
	electrum            (true,  true,   true)
	dullRedsolder       (true,  true,   true)
	redsolder           (true,  true,   true)
	HCSteel             (true,  true,   true)
	steel               (true,  true,   true)
	HSLA                (true,  true,   true)
	stainlessSteel      (true,  true,   true)
	tungstenSteel       (true,  true,   true)
	eletriplatinum      (true,  true,   true)
	mithril             (true,  true,   true)
	technomancer        (true,  true,   true)
	technomancerResonant(true,  true,   true)
	tungstenBlazing     (true,  true,   true)
	platinumGelid       (true,  true,   true)
	silverLuminous      (true,  true,   true)
	electrumFlux        (true,  true,   true)
	molybdenumResonant  (true,  true,   true)
	chromiumCarbide     (true,  true,   true)
	bismuthBronzeColdfire(true, true,   true)
	pyrum               (true,  true,   true)
	gelinium            (true,  true,   true)
	lumium              (true,  true,   true)
	signalum            (true,  true,   true)
	enderium            (true,  true,   true)
	carbonite           (true,  true,   true)
	therminate          (true,  true,   true)
	nullmetal           (true,  true,   true)
	iocarbide           (true,  true,   true)
	cryocarbide         (true,  true,   true)
	pyrocarbide         (true,  true,   true)
	tenebride           (true,  true,   true)
	illuminide          (true,  true,   true)
	zythoferride        (true,  true,   true)
	crystalFlux         (true,  true,   true*)
	lapiquartz          (true,  true,   true*)
	rust                (false, true,   false)
	sulfur              (false, true,   false)
	saltpeter           (false, true,   false)
	mithrilFlux         (true,  true,   true)
	mithrilTinker       (true,  true,   true)
	thorium             (true,  true,   true)
	U235                (true,  true,   true)
	U238                (true,  true,   true)
	magnetite           (true,  true,   true)
	neodymiumMagnet     (true,  true,   true)
	ironMagnet          (true,  true,   true)
	cobaltMagnet        (true,  true,   true)
	nickelMagnet        (true,  true,   true)
	invarMagnet         (true,  true,   true)
	HCSteelMagnet       (true,  true,   true)
	steelMagnet         (true,  true,   true)
	HSLAMagnet          (true,  true,   true)
	amber               (true,  true,   true*)
	nichrome            (true,  true,   true)
	polycarbide         (true,  true,   true)
	vorpal              (true,  true,   true)
	ashes               (false, true,   false)
	iron                (false, true,   true)
	gold                (false, true,   false)
	diamond             (false, true,   false)
	coal                (false, true,   false)
	charcoal            (false, true,   false)
	obsidian            (false, true,   true)
	blizz               (false, true,   false)
	cyrotheum           (false, true,   false)
	pyrotheum           (false, true,   false)
	iceflame            (false, true,   false)
	yttrium             (true,  true,   true)
	ruthenium           (true,  true,   true)
	lanthanum           (true,  true,   true)
	cerium              (true,  true,   true)
	magnesium           (true,  true,   true)
	calcium             (true,  true,   true)
	strontium           (true,  true,   true)
	electrinvar         (true,  true,   true)
	flint               (false, true,   false)
	clay                (true,  true,   false)
	ceramic             (true,  true,   false)
	nigelite
	nikolite
	soarynium
	jacenite
	*/

	//Gems
	/*crystalMolybdenite
	crystalDioptase
	crystalPyrope
	gemNaturalAmber
	gemMolybdenite
	gemSyntheticMolybdenite
	gemSyntheticDioptase
	gemSyntheticPyrope
	gemSyntheticGreen*/

	//10000-10999 Tiered Components
	public void loadComponents() {
		//5000-5024 Etched Wires
		//5025-5049 Energy Circuits
		//5050-5074 Data Circuits
		//5075-5099 Circuit Parts
		//5100-5124 Circuits
		//5125-5149 Transmitters
		//5150-5174 Receivers
		//5175-5199 Tranceivers
		//5200-5224 Sensors
		//5225-5249 Field Generators
		//5250-5274 Motors
		//5275-5299 Conveyors
		//5300-5324 Pistons
		//5325-5349 Robotic Arms
		//5350-5374 Capacitors
		//5375-5399 Circuits
		//5400-5424 Filters
		//5425-5449 Pipes
		//5450-5475 Ducts
		//5475-5499 Lenses
		//5500-5524 Heating Coils
		//5525-5549 Flux Coils
		//          Rotors
		//          Pumps
		//

	}

	//11000-11999 Non-Tiered Components
	public void loadParts() {
		/*
		partWiring
		partCircuitPlate
		partUnprocessedPCB
		partUnassembledPCB
		partAssembledPCB
		partTransistor
		partResistor
		partSpring
		partFluxFilter
		partIonThruster
		partResonantIonThruster
		partMagnet
		partAlCoNiMagnet
		partServoMotor
		partSolenoid
		partGearCore
		partGearBushing
		coilHeatingRedstone
		coilHeatingCupronickel
		coilSteel
		partRedLED
		partGreenLED
		partBlueLED
		partUltravioletLight
		partFluxResonator
		partTankInternal
		partTankPressurized
		coilSignalum
		circuitMagitech
		partKkaylionicRainbow
		partImpeller
		partSaw
		partRotor
		partGearbox
		partTurbine
		circuitObscurity
		partShaftSteel
		partScreen
		partPropellerBlade
		partGrindingHeadTungsten
		partFluxMotor
		partMagneticGenerator
		partElectromagnetStandard
		partElectromagnetNeodymium
		partElectrode
		partOmnidirectionalHinge
		partLaserEmitter
		partFiberOpticCable
		partMatterConverter
		partFluxLaser
		//partCopperPipe
		partPump
		partMirror
		partKeyboard
		partMouse
		partRAM
		partProcessor
		partMixer
		partHeadReader
		partLaserDetector
		partOblivionContinuum
		partCapacitorLv1
		//multicoreProcessor
		//disassemblyCore
		//enderMiningCore
		//enderCompCore
		partBlastMechanism
		heatingCoil
		//damascusSteelFrame
		//ceramicPanel
		//ceramicPanelRaw
		//ceramicMix
		//timeyWimeyCarboard
		//denseRedstone
		itemTwine
		partFluxCore
		partCryoChargingCore
		partResonantAttachments
		partFluxInductionWiring
		dustMyuvilFluxInduced
		itemYeast
		itemEthanolSludge
		partLaserPCB
		itemTeslaticRubber (Insulation for wires, prevents energy loss)
		partFluxInductionCoil
		dustSawdust
		itemCompressedSawdust
		dustFlux
		partCarbonFiber
		partCarbonMesh
		partCarbonStrand
		partExhaustTube
		partAerothermicEngine
		partEmptyEnderMagnetCore
		partEnderMagnetCore
		partInactiveEnderMagnet
		partCoilgunBarrel
		partWirelessReceiver
		partWirelessTransmitter
		partWirelessTransceiver
		partWirelessChip
		partResonantFluxCore
		partCoilgunTrigger
		partDimensionalOsmosisDampener
		partGyrorotor
		partTemporalGyrostabilizer
		partFluidLink
		partPsionometricIsolator(Powered by a Metebelis Sapphire)
		partMyufieldGenerator
		partMufieldGenerator
		*/
	}

	//12000-12999 Misc.
	public void misc() {
		//dustPitchblende
		//dustNiedermayrite
		//itemFeeshSkeleton
		//itemLatexResin
		//itemRubber
		//itemGuayuleBranches
		//itemGuayuleRubber
	}

	public static enum EnumSubTools {
		/*RUSTY_WRENCH(8),
		MAKESHIFT_WRENCH(0, "Makeshift Wrench"),
		COPPER_WRENCH(9),
		TECHNICIANS_WRENCH(10), //Bronze \\Was Simple Wrench
		TINKERERS_WRENCH(11), //AluBrass
		CRESCENT_HAMMER(), //TE Wrench
		MECHANICS_WRENCH(2, "Mechanic's Wrench"),
		INVAR_BATTLEWRENCH(), //TE Batllewrench
		KNIFEWRENCH(7, "Knife-Wrench! For kids!"),
		TINKERS_WRENCH(6, "Tinker's Wrench"),
		FLUXED_OMNIWRENCH(), //RSA Omniwrench
		LASER_WRENCH(21),
		SONIC_WRENCH(3, "Sonic Wrench"),
		DIREWOLF_WRENCH(15),
		LEMMING_WRENCH(20, "Wrench of the Lemming"),
		ENDERDOOM_WRENCH(16),
		YARKAW_WRENCH(17),
		ZIARNO_WRENCH(18),
		HOLY_WRENCH(19),






		SONIC_SPOONDRIVER(5, "Drullkus's Spoon"),
		DEBUGGING_SPORK(4, "Debugging Spork"),
		SONIC_SCREWDRIVER(22, "Sonic Screwdriver")
		;

		public int metadata;
		public String name;

		private EnumSubTools(int metadata, String name) {
			this.metadata = metadata;
			this.name = name;
		}*/
	}

	//TODO: Add Tar Pits, because Rorax
	//TODO: Wood Bucket for Water/Milk
	//TODO: Tungstensteel Bucket (holds any fluid)

	public static final String[] MORTTECH_CRAFTING_MATERIALS = new String[] {"gear", "shaft", "bevel_Mechanism", "small_Grinder", "ceramic_Plate", "ceramic_Slab", "invar_Chain", "ingot_Mould", "rope", "cutting_Blade"};

	public void ores() {
		/*
		oreChalcocite
        oreCassiterite
        oreGalena
        oreAcanthite

        oreGarnierite
        oreSphalerite
        oreBismuthinite
        orePyrolusite

        oreBauxite
        oreCooperite
        oreBraggite
        oreMolybdenite

        oreCobaltite
        oreWolframite
        oreIlmenite
        oreChromite

D		oreJacenite(Rock that carries a powerful electromagnetic charge)
1.5C	oreScryngestone(Ore of Jethrik, a rare, super-heavy, "stable" element, used in warpdrives and as a super fuel) (Iridiotungsten Hydroxyisobutylpolydioxymethyljethrik Bismosubsalicyltrialuminosilicocarbonate)
D       oreMagnetite
1.5C	oreMetebelisSapphire(Breaks hypnotic spells, enhanced intelligence & psychic powers, only source of Fluon Radiation)

RS		oreTrisilicate(Hardest form of salt)
RS		oreNikolite/oreTeslatite/oreElectrotine
RS      oreNiedermayrite
RS		oreNigelite

2D		oreAmethyst
2D		oreOnyx
RS		oreTelluriumDeposit
D		oreCarbonado

D		oreVoidstone
D		oreDioptase
D       orePyrope
RS      oreMyuvil



		oreHymetusite(Radioactive dark-gray mineral, used as powerful fuel)
        oreCinnabar
        orePitchblende
        oreMonazite

Fe      oreGreenockite
Fe      oreGaotaiite
Fe      oreOsarsite
Fe      oreZnamenskyite

Fe      oreGallobeudanite
Fe      oreTertahedrite
Fe      oreTennantite
Fe      oreSantafeite

        orePentlandite
		oreTanzanite
		oreEuxenite
RS		oreSoarynium
		*/
	}

}
