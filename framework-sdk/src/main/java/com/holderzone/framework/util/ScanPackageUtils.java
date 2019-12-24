package com.holderzone.framework.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author Mr.Q
 * @date 2019/12/24 21:59
 * desc：
 */
public class ScanPackageUtils {
    public ScanPackageUtils() {
    }

    public static Set<Class<?>> getClasses(String pack) {
        Set<Class<?>> classes = new LinkedHashSet();
        boolean recursive = true;
        String packageName = pack;
        String packageDirName = pack.replace('.', '/');

        try {
            Enumeration dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);

            while(true) {
                label69:
                while(dirs.hasMoreElements()) {
                    URL url = (URL)dirs.nextElement();
                    String protocol = url.getProtocol();
                    if ("file".equals(protocol)) {
                        System.err.println("file类型的扫描");
                        String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                        findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
                    } else if ("jar".equals(protocol)) {
                        System.err.println("jar类型的扫描");

                        try {
                            JarFile jar = ((JarURLConnection)url.openConnection()).getJarFile();
                            Enumeration entries = jar.entries();

                            while(true) {
                                JarEntry entry;
                                String name;
                                int idx;
                                do {
                                    do {
                                        if (!entries.hasMoreElements()) {
                                            continue label69;
                                        }

                                        entry = (JarEntry)entries.nextElement();
                                        name = entry.getName();
                                        if (name.charAt(0) == '/') {
                                            name = name.substring(1);
                                        }
                                    } while(!name.startsWith(packageDirName));

                                    idx = name.lastIndexOf(47);
                                    if (idx != -1) {
                                        packageName = name.substring(0, idx).replace('/', '.');
                                    }
                                } while(idx == -1 && !recursive);

                                if (name.endsWith(".class") && !entry.isDirectory()) {
                                    String className = name.substring(packageName.length() + 1, name.length() - 6);

                                    try {
                                        classes.add(Class.forName(packageName + '.' + className));
                                    } catch (ClassNotFoundException var15) {
                                        var15.printStackTrace();
                                    }
                                }
                            }
                        } catch (IOException var16) {
                            var16.printStackTrace();
                        }
                    }
                }

                return classes;
            }
        } catch (IOException var17) {
            var17.printStackTrace();
            return classes;
        }
    }

    public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, Set<Class<?>> classes) {
        File dir = new File(packagePath);
        if (dir.exists() && dir.isDirectory()) {
            File[] dirfiles = dir.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    return recursive && file.isDirectory() || file.getName().endsWith(".class");
                }
            });
            File[] var6 = dirfiles;
            int var7 = dirfiles.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                File file = var6[var8];
                if (file.isDirectory()) {
                    findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
                } else {
                    String className = file.getName().substring(0, file.getName().length() - 6);

                    try {
                        classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
                    } catch (ClassNotFoundException var12) {
                        var12.printStackTrace();
                    }
                }
            }

        }
    }
}

