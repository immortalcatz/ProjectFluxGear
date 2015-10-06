package mortvana.legacy.clean.morttech.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCrank extends ModelBase {
    //Fields
    ModelRenderer Shaft;
    ModelRenderer Handle;

    public ModelCrank() {
        textureWidth = 32;
        textureHeight = 32;

        Shaft = new ModelRenderer(this, 0, 0);
        Shaft.addBox(0F, 0F, 0F, 1, 8, 1);
        Shaft.setRotationPoint(0F, 16F, 0F);
	    Shaft.setTextureSize(32, 32);
        Shaft.mirror = true;
        setRotation(Shaft, 0F, 0F, 0F);
        Handle = new ModelRenderer(this, 5, 0);
        Handle.addBox(0F, 0F, 0F, 4, 1, 1);
        Handle.setRotationPoint(1F, 16F, 0F);
        Handle.setTextureSize(32, 32);
        Handle.mirror = true;
        setRotation(Handle, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Shaft.render(f5);
        Handle.render(f5);
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