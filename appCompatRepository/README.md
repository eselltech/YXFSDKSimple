# 本地仓库引用
    allprojects {
        repositories {
            ...
            String localrepositoryPath = "file://" + getProject().rootDir.absolutePath + File.separator + "appCompatRepository"
            maven { url localrepositoryPath }
        }
    }

