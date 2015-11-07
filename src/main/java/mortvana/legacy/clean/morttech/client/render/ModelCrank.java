package mortvana.legacy.clean.morttech.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCrank extends ModelBase {
    //Fields
    protected ModelRenderer shaft;
    protected ModelRenderer handle;

    public ModelCrank() {
        textureWidth = 32;
        textureHeight = 32;

        shaft = new ModelRenderer(this, 0, 0);
        shaft.addBox(0F, 0F, 0F, 1, 8, 1);
        shaft.setRotationPoint(0F, 16F, 0F);
	    shaft.setTextureSize(32, 32);
        shaft.mirror = true;
        setRotation(shaft, 0F, 0F, 0F);
        handle = new ModelRenderer(this, 5, 0);
        handle.addBox(0F, 0F, 0F, 4, 1, 1);
        handle.setRotationPoint(1F, 16F, 0F);
        handle.setTextureSize(32, 32);
        handle.mirror = true;
        setRotation(handle, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        shaft.render(f5);
        handle.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}