package oldcode.morttech;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TestBlock extends Block {

    public TestBlock(Material material) {
        super(material);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        this.blockIcon = register.registerIcon("morttech" + ":" + (this.getUnlocalizedName().substring(5)));
    }

}
