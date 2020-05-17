package io.izzel.arclight.mixin.core.block;

import io.izzel.arclight.bridge.inventory.IInventoryBridge;
import net.minecraft.block.BlockState;
import net.minecraft.block.ComposterBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import org.bukkit.craftbukkit.v1_14_R1.inventory.CraftBlockInventoryHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ComposterBlock.class)
public class ComposterBlockMixin {

    @Redirect(method = "createInventory", at = @At(value = "NEW", target = "net/minecraft/block/ComposterBlock.EmptyInventory"))
    public ComposterBlock.EmptyInventory arclight$newEmpty(BlockState blockState, IWorld world, BlockPos blockPos) {
        ComposterBlock.EmptyInventory inventory = new ComposterBlock.EmptyInventory();
        ((IInventoryBridge) inventory).setOwner(new CraftBlockInventoryHolder(world, blockPos, inventory));
        return inventory;
    }
}
