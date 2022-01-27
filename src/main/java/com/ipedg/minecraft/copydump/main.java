package com.ipedg.minecraft.copydump;

import org.apache.commons.io.FileUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class main extends JavaPlugin {



    @Override
    public void onEnable() {
        System.out.println("CopyPlugin 镜像插件  猪猪侠作者QQ:44920040");
        saveDefaultConfig();
        super.onEnable();
    }

    private static void copyFileUsingApacheCommonsIO(File source, File dest)
            throws IOException {
        FileUtils.copyFile(source, dest);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        File dataFolder = getDataFolder();
        for (Plugin i:getServer().getPluginManager().getPlugins()){
            JavaPlugin i1 = (JavaPlugin) i;
            try{
                Method getFileMethod = JavaPlugin.class.getDeclaredMethod("getFile");
                getFileMethod.setAccessible(true);
                File file = (File) getFileMethod.invoke(i1);
                File file1 = new File(dataFolder, file.getName());
                copyFileUsingApacheCommonsIO(file,file1);
                System.out.println("Deep Copy Success~ To:"+file.getName());
            }catch (Exception e){

            }
        }

        return super.onCommand(sender, command, label, args);
    }
}
