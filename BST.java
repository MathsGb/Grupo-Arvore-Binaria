package tree;

import lista.EstruturaDeDados;

public class BST implements EstruturaDeDados{

    private Node root;

    @Override
    public void insert(int key) {
        if (root == null){
            root = new Node(key);
        } else{
            insertNode(root, key);   
        }
    }

    private void insertNode(Node n, int key){
        if (key >= n.getValue())
        {
            //inserir na direita
            if (n.getRight() == null){
                Node newN = new Node(key);
                n.setRight(newN);
            } else {
                insertNode(n.getRight(), key);
            }
        }

        else
        {
            //inserir na esquerda
            if (n.getLeft() == null){
                Node newN = new Node(key);
                n.setLeft(newN);
            } else {
                insertNode(n.getLeft(), key);
            }
        }
    }

    @Override
    public void delete(int chave) {
        
    }

    private void deleteNode(Node n, int key){
        if (key >= n.getValue())
        {
            Node r = n.getRight();

            if (r.getValue() == key){
                //verificar se r é folha
                if (r.getRight() == null && r.getLeft() == null)
                {
                    //Caso 1
                    n.setRight(null);
                }
                else if (r.getRight() == null){
                    //Caso 2
                    n.setRight(r.getLeft());
                }
                else if (r.getLeft() == null){
                    //Caso 2
                    n.setRight(r.getRight());
                }
                else{
                    // Caso 3
                }
            }
        }
    }

    @Override
    public boolean search(int key) {
        if (root == null){
            return false;
        }
        return searchNode(root, key);
    }

    // private Node searchSpecific(int instance ,int key) {

    //     if(instance != 1 && instance != 2){
    //         System.out.println("por favor escolha entre 1('Encontra pai') ou 2('encontra nó')");
    //         return null;
    //     }
    //     else{
    //         if(instance == 1){
    //             return searchFatherNode(root, key);
    //         }
    //         return findNode(root, key);
    //     }
    // }


    private boolean searchNode(Node n, int key){
        if (n.getValue() == key){
            return true;
        } else if (key > n.getValue()){
            if (n.getRight() == null){
                return false;
            } else {
                return searchNode(n.getRight(),key);
            }
        } else {
            if (n.getLeft() == null){
                return false;
            } else {
                return searchNode(n.getLeft(),key);
            }
        }
    }

    private Node searchFather(Node pai, int key){ // 2 e chave é 0
        
        if(pai.getRight().getValue() == key || pai.getLeft().getValue() == key){
            return pai;
        }
        // if (pai.getRight() != null)
        // {
        //     if(pai.getRight().getValue() == key){
        //         return pai;
        //     }
        // }

        // else if(pai.getLeft() != null){
        //     if(pai.getLeft().getValue() == key){
        //         return pai;
        //     }
        // }

        else if (key > pai.getValue())
        {
            if (pai.getRight() == null){
                return null;
            } else {
                return searchFather(pai.getRight(),key);
            }
        }
        else
        {
            if (pai.getLeft() == null)
            {
                return null;
            }
            else{
                return searchFather(pai.getLeft(), key);
            }
        }
    }

    private Node findNode(Node atual, int key){
        if (atual.getValue() == key){
            return atual;
        } else if (key > atual.getValue()){
            if (atual.getRight() == null){
                return null;
            } else {
                return findNode(atual.getRight(),key);
            }
        } else {
            if (atual.getLeft() == null){
                return null;
            } else {
                return findNode(atual.getLeft(),key);
            }
        }
    }


    @Override
    public int minimum() {

        return minimumNode(root).getValue();
    }

    private Node minimumNode(Node no){
        if(no.getLeft() == null){
            return no;
        }
        else{
            return minimumNode(no.getLeft());
        }
    }

    @Override
    public int maximum() {
        return maximumNode(root).getValue();
    }

    private Node maximumNode(Node no){
        if(no.getRight() == null){
            return no;
        }
        else{
            return maximumNode(no.getRight());
        }
    }

    @Override
    public int sucessor(int chave) {

        return searchSucessor(findNode(root, chave), chave);
        // Boolean verify = search(chave);

        // if(verify == null){ // Caso não haja um nó
        //     System.out.println("Não há sucessor");
        //     return -1 ; 
        // }

        // Node temp = findNode(root, chave);  //Variável para achar o nó que estou trabalhando.

        // Node Pai = searchFather(root, chave);
        
        // if(temp.getRight() == null){ // Representa que "temp" é em uma Folha.
            
        //     if(Pai.getLeft() == temp){ // Para o caso de o filho ser pela esquerda(pai é o seu sucessor)
        //         return Pai.getValue();
        //     }

        //     else{
                
        //         return chave ; 
        //     }
        // }

        // else{ // Para o caso onde o nó não é uma folha
           
        //     return searchSucessor(temp).getValue();
        // }
    }
    
    private int searchSucessor(Node atual, int chave){
        
        if(atual.getRight() != null){
            return minimumNode(atual.getRight()).getValue();
        }
        else if(atual.getLeft() != null ){
            return maximumNode(atual.getRight()).getValue();
        }
        else{

            return -1;
        }

        }
        //     int maisProximo = atual.getValue();
        //     if(atual.getLeft() == null){
        //         return maisProximo;
        //     } else{
        //         return searchSucessor(atual.get(), chave);
        //     }
        // }
        // if(chave > atual.getValue()){
        //     int maisProximo = atual.getValue();
        //     if(atual.getRight() == null){
        //         return maisProximo;
        //     } else{
        //         return searchSucessor(atual.get(), chave);
        //     }
        // }
    


    @Override
    public int prodessor(int chave) {
        Node temp = search(chave);

        if(temp == null){
            return null;
        }

        temp = findNode(root, chave);

        if(temp.getRight() == null && temp.getLeft() == null){
            return searchFather(root, chave).getValue();
        }

        else{
            return searchSucesor().getValue();
        }
        return 0;
    }

    public static void main(String[] args) {
        BST tree = new BST();
        
        tree.insert(4);
        tree.insert(0);
        tree.insert(2);
        tree.insert(3);
        tree.insert(1);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        // System.out.println(tree.search(5));
        // System.out.println(tree.maximum());
        // System.out.println(tree.search(7));
        
        System.out.println(tree.sucessor());
    }
}