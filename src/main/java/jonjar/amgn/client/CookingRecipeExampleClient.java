package jonjar.amgn.client;

import jonjar.amgn.element.screen.TestFurnaceScreen;
import jonjar.amgn.registry.CookingRecipeExample;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

@Environment(EnvType.CLIENT)
public class CookingRecipeExampleClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(CookingRecipeExample.TEST_FURNACE_SCREEN_HANDLER, TestFurnaceScreen::new);
    }
}