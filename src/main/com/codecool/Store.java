package main.com.codecool;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;


import java.util.ArrayList;
import java.util.List;
import java.lang.Integer;


public abstract class Store implements StorageCapable {

    private List<Product> products = new ArrayList<>();



    List<Product> getProducts() {
        return products;
    }

    private void saveToXml(String filename) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filename));

            Element rootElement = doc.createElement("Store");
            doc.appendChild(rootElement);

            for (int i = 0; i < getAllProduct().size() ; i++) {
                if (getAllProduct().get(i) instanceof CDProduct) {
                    Element product1 = doc.createElement("Product");
                    rootElement.appendChild(product1);
                    Attr attr = doc.createAttribute("type");
                    attr.setValue("cd");
                    product1.setAttributeNode(attr);

                    Element name = doc.createElement("name");
                    name.appendChild(doc.createTextNode(getAllProduct().get(i).getName()));
                    product1.appendChild(name);

                    Element price = doc.createElement("price");
                    price.appendChild(doc.createTextNode(Integer.toString(getAllProduct().get(i).getPrice())));
                    product1.appendChild(price);

                    Element numOfTracks = doc.createElement("tracks");
                    numOfTracks.appendChild(doc.createTextNode(Integer.toString(((CDProduct) getAllProduct().get(i)).getNumOfTracks())));
                    product1.appendChild(numOfTracks);

                    transformer.transform(source, result);

                } else if (getAllProduct().get(i) instanceof BookProduct) {
                    Element product1 = doc.createElement("Product");
                    rootElement.appendChild(product1);
                    Attr attr = doc.createAttribute("type");
                    attr.setValue("book");
                    product1.setAttributeNode(attr);

                    Element name = doc.createElement("name");
                    name.appendChild(doc.createTextNode(getAllProduct().get(i).getName()));
                    product1.appendChild(name);

                    Element price = doc.createElement("price");
                    price.appendChild(doc.createTextNode(Integer.toString(getAllProduct().get(i).getPrice())));
                    product1.appendChild(price);

                    Element numOfTracks = doc.createElement("pages");
                    numOfTracks.appendChild(doc.createTextNode(Integer.toString(((BookProduct) getAllProduct().get(i)).getNumOfPages())));
                    product1.appendChild(numOfTracks);

                    transformer.transform(source, result);
                }
            }
        } catch (ParserConfigurationException pcex) {
            pcex.printStackTrace();
        } catch (TransformerException tfex) {
            tfex.printStackTrace();
        }
    }


    protected abstract void storeProduct(Product product);


    private Product createProduct(String type, String name, int price, int size) {
        Product product = null;
        try {
            if (type.toLowerCase().equals("book")) {
                product = new BookProduct(name, price, size);
            } else if (type.toLowerCase().equals("cd")) {
                product = new CDProduct(name, price, size);
            }
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            System.out.println("Not valid type! (Try cd or book!)");
        }
        return product;
    }

    public List<Product> loadProducts(){
        List<Product> products = new ArrayList<Product>();

        try {
            File storage = new File("Products.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(storage);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("product");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    if (eElement.getElementsByTagName("type").item(0).getTextContent().equals("CD")) {
                        String name = eElement.getElementsByTagName("name").item(0).getTextContent();
                        int price = Integer.parseInt(eElement.getElementsByTagName("price").item(0).getTextContent());
                        int size = Integer.parseInt(eElement.getElementsByTagName("size").item(0).getTextContent());
                        products.add(createProduct("CD", name, price, size));
                    } else {
                        String name = eElement.getElementsByTagName("name").item(0).getTextContent();
                        int price = Integer.parseInt(eElement.getElementsByTagName("price").item(0).getTextContent());
                        int size = Integer.parseInt(eElement.getElementsByTagName("size").item(0).getTextContent());
                        products.add(createProduct("Book", name, price, size));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }


    void store(String filename){
        saveToXml(filename);
    }

    public List<Product> getAllProduct() {
        return products;
    }

    @Override
    public void storeCDProduct(String name, int price, int tracks) {
        storeProduct(createProduct("cd", name, price, tracks));
    }

    @Override
    public void storeBookProduct(String name, int price, int pages) {
        storeProduct(createProduct("book", name, price, pages));
    }
}
