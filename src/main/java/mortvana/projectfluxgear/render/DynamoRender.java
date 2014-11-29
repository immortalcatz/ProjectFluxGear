package mortvana.projectfluxgear.render;

/*public class RenderDynamo implements ISimpleBlockRenderingHandler {
    public static final RenderDynamo instance = new RenderDynamo();
    static IIcon textureCoil;
    static IIcon[] textureBase = new IIcon[BlockDynamo.Types.values().length];
    static CCModel[][] modelCoil = new CCModel[2][6];
    static CCModel[][] modelBase = new CCModel[2][6];
    static CCModel[] modelAnimation = new CCModel[6];

    static {
        TEProps.renderIdDynamo = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(instance);

        generateModels();
    }

    public static void initialize() {
        textureCoil = IconRegistry.getIcon("DynamoCoilRedstone");
        for (int i = 0; i < textureBase.length; i++) {
            textureBase[i] = IconRegistry.getIcon("Dynamo", i);
        }
    }

    private static void generateModels() {
        double d1 = 9.765625E-4D;
        double d2 = 0.375D;
        double d3 = 0.625D;

        modelCoil[0][1] = CCModel.quadModel(24).generateBox(0, -4.0D, 0.0D, -4.0D, 8.0D, 8.0D, 8.0D, 0.0D, 0.0D, 32.0D, 32.0D, 16.0D).computeNormals().shrinkUVs(d1);
        modelCoil[1][1] = CCModel.quadModel(24).generateBox(0, -4.0D, 0.0D, -4.0D, 8.0D, 8.0D, 8.0D, 0.0D, 16.0D, 32.0D, 32.0D, 16.0D).computeNormals().shrinkUVs(d1);

        modelBase[0][1] = CCModel.quadModel(24).generateBox(0, -8.0D, -8.0D, -8.0D, 16.0D, 10.0D, 16.0D, 0.0D, 0.0D, 64.0D, 64.0D, 16.0D).computeNormals().shrinkUVs(d1);
        modelBase[1][1] = CCModel.quadModel(24).generateBox(0, -8.0D, -8.0D, -8.0D, 16.0D, 10.0D, 16.0D, 0.0D, 32.0D, 64.0D, 64.0D, 16.0D).computeNormals().shrinkUVs(d1);

        modelAnimation[0] = CCModel.quadModel(16).generateBlock(0, d1, d2 - d1, d1, 1.0D - d1, 1.0D - d1, 1.0D - d1, 3).computeNormals();
        modelAnimation[1] = CCModel.quadModel(16).generateBlock(0, d1, d1, d1, 1.0D - d1, d3 - d1, 1.0D - d1, 3).computeNormals();

        modelAnimation[2] = CCModel.quadModel(16).generateBlock(0, d1, d1, d2 - d1, 1.0D - d1, 1.0D - d1, 1.0D - d1, 12).computeNormals();
        modelAnimation[3] = CCModel.quadModel(16).generateBlock(0, d1, d1, d1, 1.0D - d1, 1.0D - d1, d3 - d1, 12).computeNormals();

        modelAnimation[4] = CCModel.quadModel(16).generateBlock(0, d2 - d1, d1, d1, 1.0D - d1, 1.0D - d1, 1.0D - d1, 48).computeNormals();
        modelAnimation[5] = CCModel.quadModel(16).generateBlock(0, d1, d1, d1, d3 - d1, 1.0D - d1, 1.0D - d1, 48).computeNormals();
        for (int i = 0; i < modelCoil.length; i++) {
            CCModel.generateSidedModels(modelCoil[i], 1, new Vector3());
        }
        for (i = 0; i < modelBase.length; i++) {
            CCModel.generateSidedModels(modelBase[i], 1, new Vector3());
        }
    }

    public void renderCoil(int paramInt, boolean paramBoolean, double paramDouble1, double paramDouble2, double paramDouble3) {
        paramDouble1 += 0.5D;
        paramDouble2 += 0.5D;
        paramDouble3 += 0.5D;

        Translation localTranslation = RenderUtils.getRenderVector(paramDouble1, paramDouble2, paramDouble3).translation();
        if (paramBoolean) {
            modelCoil[0][paramInt].render(new CCRenderState.IVertexOperation[] { localTranslation, RenderUtils.getIconTransformation(textureCoil) });
        } else {
            modelCoil[1][paramInt].render(new CCRenderState.IVertexOperation[] { localTranslation, RenderUtils.getIconTransformation(textureCoil) });
        }
    }

    public void renderBase(int paramInt1, boolean paramBoolean, int paramInt2, double paramDouble1, double paramDouble2, double paramDouble3) {
        paramDouble1 += 0.5D;
        paramDouble2 += 0.5D;
        paramDouble3 += 0.5D;

        Translation localTranslation = RenderUtils.getRenderVector(paramDouble1, paramDouble2, paramDouble3).translation();
        if (paramBoolean) {
            modelBase[0][paramInt1].render(new CCRenderState.IVertexOperation[] { localTranslation, RenderUtils.getIconTransformation(textureBase[paramInt2]) });
        } else {
            modelBase[1][paramInt1].render(new CCRenderState.IVertexOperation[] { localTranslation, RenderUtils.getIconTransformation(textureBase[paramInt2]) });
        }
    }

    public void renderAnimation(int paramInt1, boolean paramBoolean, int paramInt2, IIcon paramIIcon, double paramDouble1, double paramDouble2, double paramDouble3) {
        if (paramBoolean) {
            modelAnimation[paramInt1].render(paramDouble1, paramDouble2, paramDouble3, RenderUtils.getIconTransformation(paramIIcon));
        }
    }

    public void renderInventoryBlock(Block paramBlock, int paramInt1, int paramInt2, RenderBlocks paramRenderBlocks) {
        RenderUtils.preItemRender();

        CCRenderState.startDrawing();
        renderCoil(1, false, -0.5D, -0.5D, -0.5D);
        renderBase(1, false, paramInt1, -0.5D, -0.5D, -0.5D);
        CCRenderState.draw();

        RenderUtils.postItemRender();
    }

    public boolean renderWorldBlock(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3, Block paramBlock, int paramInt4, RenderBlocks paramRenderBlocks) {
        TileEntity localTileEntity = paramIBlockAccess.getTileEntity(paramInt1, paramInt2, paramInt3);
        if (!(localTileEntity instanceof TileDynamoBase)) {
            return false;
        }
        TileDynamoBase localTileDynamoBase = (TileDynamoBase)localTileEntity;

        RenderUtils.preWorldRender(paramIBlockAccess, paramInt1, paramInt2, paramInt3);
        renderCoil(localTileDynamoBase.getFacing(), localTileDynamoBase.isActive, paramInt1, paramInt2, paramInt3);
        renderAnimation(localTileDynamoBase.getFacing(), localTileDynamoBase.isActive, localTileDynamoBase.getType(), localTileDynamoBase.getActiveIcon(), paramInt1, paramInt2, paramInt3);
        renderBase(localTileDynamoBase.getFacing(), localTileDynamoBase.isActive, localTileDynamoBase.getType(), paramInt1, paramInt2, paramInt3);

        return true;
    }

    public boolean shouldRender3DInInventory(int paramInt) {
        return true;
    }

    public int getRenderId() {
        return TEProps.renderIdDynamo;
    }

    public RenderDynamo() {}
}*/
