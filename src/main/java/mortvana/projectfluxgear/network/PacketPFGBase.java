package mortvana.projectfluxgear.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import cofh.api.tileentity.IRedstoneControl;
import cofh.api.tileentity.ISecurable;
import cofh.core.network.PacketCoFHBase;
import cofh.core.network.PacketHandler;

import mortvana.projectfluxgear.common.ProjectFluxGear;

public class PacketPFGBase extends PacketCoFHBase
{
    public static void initialize()
    {
        PacketHandler.instance.registerPacket(PacketPFGBase.class);
    }

    public static enum PacketTypes
    {
        RS_POWER_UPDATE,  RS_CONFIG_UPDATE,  SECURITY_UPDATE,  TAB_AUGMENT,  TAB_SCHEMATIC,  CONFIG_SYNC;

        private PacketTypes() {}
    }

    public void handlePacket(EntityPlayer paramEntityPlayer, boolean paramBoolean)
    {
        try
        {
            int i = getByte();
            int[] arrayOfInt;
            IRedstoneControl localIRedstoneControl;
            /*switch (1.$SwitchMap$mortvana$thermaltinkerer$network$PacketTTiBase$PacketTypes[PacketTypes.values()[i].ordinal()])
            {
                case 1:
                    arrayOfInt = getCoords();
                    localIRedstoneControl = (IRedstoneControl)paramEntityPlayer.worldObj.getTileEntity(arrayOfInt[0], arrayOfInt[1], arrayOfInt[2]);
                    localIRedstoneControl.setPowered(getBool());
                    return;
                case 2:
                    arrayOfInt = getCoords();
                    localIRedstoneControl = (IRedstoneControl)paramEntityPlayer.worldObj.getTileEntity(arrayOfInt[0], arrayOfInt[1], arrayOfInt[2]);
                    localIRedstoneControl.setControl(IRedstoneControl.ControlMode.values()[getByte()]);
                    return;
                case 3:
                    if ((paramEntityPlayer.openContainer instanceof ISecurable)) {
                        ((ISecurable)paramEntityPlayer.openContainer).setAccess(ISecurable.AccessMode.values()[getByte()]);
                    }
                    return;
                case 4:
                    if ((paramEntityPlayer.openContainer instanceof IAugmentableContainer)) {
                        ((IAugmentableContainer)paramEntityPlayer.openContainer).setAugmentLock(getBool());
                    }
                    return;
                case 5:
                    if ((paramEntityPlayer.openContainer instanceof ISchematicContainer)) {
                        ((ISchematicContainer)paramEntityPlayer.openContainer).writeSchematic();
                    }
                    return;
                case 6:
                    ThermalTinkerer.instance.handleConfigSync(this);
                    return;
            }*/
            ProjectFluxGear.logger.error("Unknown Packet! Internal: TEPH, ID: " + i);
        }
        catch (Exception localException)
        {
            ProjectFluxGear.logger.error("Packet payload failure! Please check your config files!");
            localException.printStackTrace();
        }
    }

    public static void sendRSPowerUpdatePacketToClients(IRedstoneControl paramIRedstoneControl, World paramWorld, int paramInt1, int paramInt2, int paramInt3)
    {
        PacketHandler.sendToAllAround(getPacket(PacketTypes.RS_POWER_UPDATE).addCoords(paramInt1, paramInt2, paramInt3).addBool(paramIRedstoneControl.isPowered()), paramWorld, paramInt1, paramInt2, paramInt3);
    }

    public static void sendRSConfigUpdatePacketToServer(IRedstoneControl paramIRedstoneControl, int paramInt1, int paramInt2, int paramInt3)
    {
        PacketHandler.sendToServer(getPacket(PacketTypes.RS_CONFIG_UPDATE).addCoords(paramInt1, paramInt2, paramInt3).addByte(paramIRedstoneControl.getControl().ordinal()));
    }

    public static void sendSecurityPacketToServer(ISecurable paramISecurable)
    {
        PacketHandler.sendToServer(getPacket(PacketTypes.SECURITY_UPDATE).addByte(paramISecurable.getAccess().ordinal()));
    }

    public static void sendTabAugmentPacketToServer(boolean paramBoolean)
    {
        PacketHandler.sendToServer(getPacket(PacketTypes.TAB_AUGMENT).addBool(paramBoolean));
    }

    public static void sendTabSchematicPacketToServer()
    {
        PacketHandler.sendToServer(getPacket(PacketTypes.TAB_SCHEMATIC));
    }

    public static void sendConfigSyncPacketToClient(EntityPlayer paramEntityPlayer)
    {
       // PacketHandler.sendTo(ThermalTinkerer.instance.getConfigSync(), paramEntityPlayer);
    }

    public static PacketCoFHBase getPacket(PacketTypes paramPacketTypes)
    {
        return new PacketPFGBase().addByte(paramPacketTypes.ordinal());
    }
}
