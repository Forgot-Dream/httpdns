package cn.forgotdream.httpdns.mixin;

import cn.forgotdream.httpdns.util.HttpParser;
import com.mojang.datafixers.util.Pair;
import net.minecraft.network.ServerAddress;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(ServerAddress.class)
abstract class MixinServerAddress{

    @Inject(method = "resolveServer",at = @At("TAIL"), cancellable = true)
    private static void resolveServer(String address, CallbackInfoReturnable<Pair<String, Integer>> cir){
        HttpParser httpParser = new HttpParser(address);
        if(!httpParser.GetIP().isEmpty()){
            cir.setReturnValue(Pair.of(httpParser.GetIP(), 25565));
        }
    }

}
