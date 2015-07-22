package mortvana.legacy.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class GuayuleBushFancyRenderer extends ModelBase {
    public ModelRenderer Trunk;
    public ModelRenderer leafLayer1;
    public ModelRenderer leafLayer2;
    public ModelRenderer shape4;
    public ModelRenderer shape4_1;
    public ModelRenderer shape4_2;
    public ModelRenderer shape4_3;

    public GuayuleBushFancyRenderer() {
        textureWidth = 64;
        textureHeight = 64;
        shape4 = new ModelRenderer(this, 16, 0);
        shape4.setRotationPoint(1.0F, 14.0F, 1.0F);
        shape4.addBox(0.0F, -6.5F, 0.0F, 2, 8, 2);
        setRotateAngle(shape4, -0.39269908169872414F, 0.0F, 0.39269908169872414F);
        leafLayer1 = new ModelRenderer(this, 24, 0);
        leafLayer1.setRotationPoint(-5.0F, 10.0F, -5.0F);
        leafLayer1.addBox(0.0F, 0.0F, 0.0F, 10, 10, 10);
        Trunk = new ModelRenderer(this, 0, 0);
        Trunk.setRotationPoint(-2.0F, 14.0F, -2.0F);
        Trunk.addBox(0.0F, 0.0F, 0.0F, 4, 10, 4);
        leafLayer2 = new ModelRenderer(this, 0, 20);
        leafLayer2.setRotationPoint(-6.0F, 8.0F, -6.0F);
        leafLayer2.addBox(0.0F, 0.0F, 0.0F, 12, 13, 12);
        shape4_1 = new ModelRenderer(this, 16, 0);
        shape4_1.setRotationPoint(1.0F, 14.0F, -1.0F);
        shape4_1.addBox(0.0F, -6.5F, -2.0F, 2, 8, 2);
        setRotateAngle(shape4_1, 0.39269908169872414F, 0.0F, 0.39269908169872414F);
        shape4_2 = new ModelRenderer(this, 16, 0);
        shape4_2.setRotationPoint(-1.0F, 14.0F, 1.0F);
        shape4_2.addBox(-2.0F, -6.5F, 0.0F, 2, 8, 2);
        setRotateAngle(shape4_2, -0.39269908169872414F, 0.0F, -0.39269908169872414F);
        shape4_3 = new ModelRenderer(this, 16, 0);
        shape4_3.setRotationPoint(-1.0F, 14.0F, -1.0F);
        shape4_3.addBox(-2.0F, -6.5F, -2.0F, 2, 8, 2);
        setRotateAngle(shape4_3, 0.39269908169872414F, 0.0F, -0.39269908169872414F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        shape4.render(f5);
        leafLayer1.render(f5);
        Trunk.render(f5);
        leafLayer2.render(f5);
        shape4_1.render(f5);
        shape4_2.render(f5);
        shape4_3.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
