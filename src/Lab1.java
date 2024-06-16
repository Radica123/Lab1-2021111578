import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
//To explore strange new worlds,
//To seek out new life and new civilizations AFTER
public class Lab1 {
    public Lab1() {

    }
    //辅助函数，用来规格化输入句子
    public List<String> stringset(String inputstr) {
        List<String> finals = new ArrayList<>();
        for (String tmp : inputstr.toLowerCase().replaceAll("[^a-z]", " ").split(" ")) {
            if (!tmp.isEmpty()) {
                finals.add(tmp.replace(" ", ""));
            }
        }
        return finals;
    }
    //辅助函数，用来把句子变成token串
    public List<String> tokens(List<String> finals) {

        List<String> tokens = new ArrayList<>(finals);
        for (int i = 0; i < tokens.size(); i++) {
            for (int j = i; j < tokens.size(); j++) {
                if (tokens.get(i).equals(tokens.get(j)) && i != j) {
                    tokens.remove(j);
                }
            }
        }
        return tokens;
    }


    public graph makegraph() {
        List<String> preGraph;
        List<String> set;
        graph g = new graph();
        System.out.print("请输入txt路径 :");
        String pathtest = "test2.txt";
        String str = null;
        try {
            str = Files.readString(Paths.get(pathtest));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        preGraph = stringset(str);
        set = tokens(preGraph);
        for (String s : set) {
            graphnode node = new graphnode(s);
            g.addNode(node);}
        for (int i = 0; i < preGraph.size(); i++) {
            if (i == 0) {
                for (graphnode m : g.nodes) {
                    if (Objects.equals(m.node, preGraph.get(i))) {
                        for (graphnode m1 : g.nodes) {
                            if (Objects.equals(m1.node, preGraph.get(i + 1))) {
                                m.addRight(m1);
                            }
                        }
                    }
                }
            } else if (i == preGraph.size() - 1) {

            } else {
                for (graphnode m : g.nodes) {
                    if (Objects.equals(m.node, preGraph.get(i))) {
                        for (graphnode m1 : g.nodes) {
                            if (Objects.equals(m1.node, preGraph.get(i + 1))) {
                                m.addRight(m1);
                            }
                        }
                    }
                }
            }
        }
        return g;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入想要的起点单词: ");
        String startWord = scanner.nextLine();
        System.out.print("请输入想要的终点单词: ");
        String endWord = scanner.nextLine();

        Lab1 obj = new Lab1();
        graph g=obj.makegraph();


        // Find start and end nodes
        graphnode startNode = null;
        graphnode endNode = null;
        for (graphnode node : g.nodes) {
            if (node.node.equals(startWord)) {
                startNode = node;
            }
            if (node.node.equals(endWord)) {
                endNode = node;
            }
        }

        if (startNode == null || endNode == null) {
            System.out.println("起点或终点单词不存在图中！");
            System.exit(1);
        }

        // Find paths
        List<String> path = new ArrayList<>();
        List<List<String>> paths = new ArrayList<>(); // 两点间的所有路径
        path.add(startNode.node);
        g.FindPaths(startNode, endNode, path, paths);

        System.out.println("find path");
        System.out.println(paths.toString());
        System.out.println("---------");

        List<String> bridgeWord1=g.queryBridgeWords(startWord,endWord);
        if ((!bridgeWord1.isEmpty()))
        {
            System.out.println("下列是" + startWord + "和" + endWord + "的bridge words:");
            for(String str1:bridgeWord1){
                System.out.println(str1);
            }
        }
        else{
            System.out.println("No bridge words from word1 to " + "word2!");
        }


        //显示有向图
        g.showDirectedGraph();


        //显示标记了的有向图
        g.displaystressedGraph(paths);


        //插入bridge words
        System.out.print("请输入要扩充的句子");
        String tobebrigded = scanner.nextLine();

        System.out.println(g.generateNewText(tobebrigded));


        // 6
        g.randomWalk();

    }
}







