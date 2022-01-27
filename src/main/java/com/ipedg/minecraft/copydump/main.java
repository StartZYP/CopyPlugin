package com.ipedg.minecraft.copydump;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.file.Files;

public class main extends JavaPlugin {
    private static void copyFileUsingJava7Files(File source, File dest) throws IOException {
        Files.copy(source.toPath(), dest.toPath());
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        File dataFolder = getDataFolder();
        if (!dataFolder.exists()){
            dataFolder.mkdir();
        }
        for (Plugin i:getServer().getPluginManager().getPlugins()){
            JavaPlugin i1 = (JavaPlugin) i;
            try{
                Method getFileMethod = JavaPlugin.class.getDeclaredMethod("getFile");
                getFileMethod.setAccessible(true);
                File file = (File) getFileMethod.invoke(i1);
                File file1 = new File(dataFolder, file.getName());
                copyFileUsingJava7Files(file,file1);
                System.out.println("Deep Copy Success~ To:"+file.getName());
            }catch (Exception e){

            }
        }
        return super.onCommand(sender, command, label, args);
    }
}
