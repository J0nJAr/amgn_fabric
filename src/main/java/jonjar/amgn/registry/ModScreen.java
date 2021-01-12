package jonjar.amgn.registry;

import jonjar.amgn.Amgn;
import jonjar.amgn.element.screen.PulverizerScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreen {
    public static ScreenHandlerType<PulverizerScreenHandler> PULVERIZER_SCREEN_HANDLER;

    static {
        PULVERIZER_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(Amgn.MODID, "test_furnace"), PulverizerScreenHandler::new);
    }
    public void registerScreens(){
        System.out.println("스크린 등록");
    }
}
