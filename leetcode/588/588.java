class FileSystem {
    FileNode root;
    public FileSystem() {
        root = new FileNode();
    }
    
    public List<String> ls(String path) {
        FileNode node = getToPointLocation(root, path);
        List<String> fileNames = new ArrayList<>(getFileNamesInDirOrItSelf(node));
        fileNames.sort((String s1, String s2) -> {
            return s1.compareTo(s2);
        });
        return fileNames;
    }
    
    public void mkdir(String path) {
        createFileByPath(root, path, null, 1);
    }
    
    public void addContentToFile(String filePath, String content) {
        FileNode node = getToPointLocation(root, filePath);
        if(node == null) {
            node = createFileByPath(root, filePath, new StringBuilder(content), 2);
            return;
        }
        node.content.append(content);
    }
    
    public String readContentFromFile(String filePath) {
        FileNode node = getToPointLocation(root, filePath);
        if(node == null) {
            return "";
        }
        return node.content.toString();
    }

    public List<String> getFileNamesInDirOrItSelf(FileNode node) {
        List<String> fileNames = new ArrayList<>();
        if(node == null) {
            return fileNames;
        }

        if(node.type == 2) {
            fileNames.add(node.name);
            return fileNames;
        }
        List<FileNode> files = node.children;
        for(FileNode file : files) {
            fileNames.add(file.name);
        }
        return fileNames;
    }

    public FileNode getToPointLocation(FileNode root, String path) {
        if(path.equals("/")) {
            return root;
        }

        String[] elements = path.split("/");
        FileNode node = root;
        for(int i = 0; i < elements.length; i++) {
            String element = elements[i];
            if(element.equals("")) {
                continue;
            }
            HashMap<String, Integer> toIndex = node.toIndex;
            if(toIndex.containsKey(element)) {
                node = node.children.get(toIndex.get(element));
                continue;
            } 
            return null;
        }
        return node;
    }

    public FileNode createFileByPath(FileNode root, String path, StringBuilder content, int type) {
        if(path.equals("/")) {
            return root;
        }

        String[] elements = path.split("/");
        int len = elements.length;
        FileNode node = root, newNode = null;
        for(int i = 0; i < len; i++) {
            String element = elements[i];
            if(element.equals("")) {
                continue;
            }
            HashMap<String, Integer> toIndex = node.toIndex;
            if(toIndex.containsKey(element)) {
                node = node.children.get(toIndex.get(element));
            } else {
                if(i < len - 1) {
                    newNode = new FileNode(1, element, null);
                } else {
                    newNode = new FileNode(type, element, content);
                }
                node.children.add(newNode);
                toIndex.put(element, node.children.size() - 1);
                node = newNode;
                newNode = null;
            }
        }
        return node;
    }
}

class FileNode {
    public int type;
    public String name;
    public List<FileNode> children;
    public StringBuilder content;
    public HashMap<String, Integer> toIndex;
    public FileNode(int type, String name, StringBuilder content) {
        this.type = type;
        this.name = name;
        this.content = content;
        children = new ArrayList<>();
        toIndex = new HashMap<>();
    }

    public FileNode() {
        children = new ArrayList<>();
        toIndex = new HashMap<>();
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */