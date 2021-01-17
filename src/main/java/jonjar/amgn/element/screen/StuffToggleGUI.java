package jonjar.amgn.element.screen;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.TooltipBuilder;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WToggleButton;
import jonjar.amgn.Amgn;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.LiteralText;

public class StuffToggleGUI extends LightweightGuiDescription {

    public StuffToggleGUI(){
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(300, 200);

        WLabel title = new WLabel(new LiteralText("김베포 장난질 토글러"), WLabel.DEFAULT_TEXT_COLOR){
            @Environment(EnvType.CLIENT)
            @Override
            public void addTooltip(TooltipBuilder tt){
                tt.add(new LiteralText("아아~ NAGA~"));
            }
        };
        root.add(title, 0, 0);

        WToggleButton button = new WToggleButton(new LiteralText("중력 500배"));

        button.setToggle(Amgn.TOGGLED_GRAVITY);
        button.setOnToggle(on -> {
            Amgn.TOGGLED_GRAVITY = on;
        });
        root.add(button, 0, 1);
    }

}
