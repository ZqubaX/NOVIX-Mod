
package com.novix.menu;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class NovixMenu implements ClientModInitializer {

    private static KeyBinding openMenu;

    @Override
    public void onInitializeClient() {
        openMenu = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(
                "key.novixmenu.open",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                new KeyBinding.Category("novixmenu")
            )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openMenu.wasPressed()) {
                if (client.currentScreen == null) {
                    client.setScreen(new NovixScreen());
                }
            }
        });
    }

    public static class NovixScreen extends Screen {

        protected NovixScreen() {
            super(Text.literal("NOVIX CLIENT"));
        }

        @Override
        public void render(DrawContext context, int mouseX, int mouseY, float delta) {
            this.renderBackground(context, mouseX, mouseY, delta);

            int centerX = this.width / 2;
            int centerY = this.height / 2;

            context.fill(
                centerX - 150, centerY - 110,
                centerX + 150, centerY + 110,
                0xEE101018
            );

            context.drawCenteredTextWithShadow(
                this.textRenderer,
                "NOVIX CLIENT",
                centerX,
                centerY - 90,
                0xFF55DDFF
            );

            context.drawCenteredTextWithShadow(
                this.textRenderer,
                "COMBAT     MOVEMENT     VISUALS",
                centerX,
                centerY - 45,
                0xFFFFFFFF
            );

            context.drawCenteredTextWithShadow(
                this.textRenderer,
                "NOVIX menu is running",
                centerX,
                centerY + 5,
                0xFFAAAAAA
            );

            context.drawCenteredTextWithShadow(
                this.textRenderer,
                "Press ESC to close",
                centerX,
                centerY + 85,
                0xFF888888
            );
        }
    }
}
