import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.*;
import javax.xml.xpath.*;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;


public class test3 {
public File saveFile;
    public static void main(String[] args) {
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
      Element theBook=null, theElem=null, root=null;
        try {
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder db=factory.newDocumentBuilder();
            Document doc=db.parse(new File("D:/Papyrus/workspace/map/map.uml"));
            root=doc.getDocumentElement();

            output(doc);
FileWriter fw3=new FileWriter("saveFile.txt",true);
fw3.write("#end");
fw3.close();

	int rtc = 0,rtv = 0,rtt = 0; 
        FileReader fr = new FileReader("saveFile.txt");
        BufferedReader br = new BufferedReader(fr);
	String bustr = "";
        while ((bustr=br.readLine()) != null) {
		if (bustr.indexOf("#class") != -1) {
		rtc = rtc + 1;
		rtv = 0;
		} else {
			if (bustr.indexOf("#end") == -1) {
			rtv = rtv + 1;
			if (rtv > rtt) {
				rtt = rtv;
					}
			}
		}
        }
        fr.close();

String[][] tempArray = new String[rtc][rtt];

	rtc = 0;
	rtv = 0;
	FileReader fr2 = new FileReader("saveFile.txt"); 
        BufferedReader br2 = new BufferedReader(fr2);
	bustr = "";
        while ((bustr=br2.readLine()) != null) {
		if (bustr.indexOf("#class") != -1) {
		rtv = 0;
		tempArray[rtc][rtv] = bustr;
		rtc = rtc + 1;
		} else {
			if (bustr.indexOf("#end") == -1) {
			tempArray[rtc-1][rtv] = bustr;
			rtv = rtv + 1;
			}
		}
        }

        fr2.close();

FileWriter fws=new FileWriter("test.html");
fws.write("");
fws.close();
FileWriter fwm=new FileWriter("test.html",true);
fwm.write("<!DOCTYPE html>\r\n");
fwm.write("<html>\r\n");
fwm.write("	<head>\r\n");
fwm.write("		<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n");
fwm.write("		<meta name=\"viewport\" content=\"initial-scale=1.0, user-scalable=no\" />\r\n");
fwm.write("		<title>Demo</title>\r\n");
fwm.write("		<style>\r\n");
fwm.write("			#map {\r\n");
fwm.write("				height: 100%;\r\n");
fwm.write("			}\r\n");
fwm.write("			#layer {\r\n");
fwm.write("				position: absolute;\r\n");
fwm.write("				top: 50px;\r\n");
fwm.write("				left: 10px;\r\n");
fwm.write("				z-index: 5;\r\n");
fwm.write("				background-color: #fff;\r\n");
fwm.write("				padding: 5px;\r\n");
fwm.write("				border: 1px solid #999;\r\n");
fwm.write("				font-family: 'Roboto','sans-serif';\r\n");
fwm.write("				line-height: 10px;\r\n");
fwm.write("				padding-top: 10px;\r\n");
fwm.write("				padding-bottom: 10px;\r\n");
fwm.write("				padding-left: 10px;\r\n");
fwm.write("				padding-right: 10px;\r\n");
fwm.write("			}\r\n");
fwm.write("			html, body {\r\n");
fwm.write("				height: 100%;\r\n");
fwm.write("				margin: 0;\r\n");
fwm.write("				padding: 0;\r\n");
fwm.write("			}\r\n");
fwm.write("		</style>\r\n");
fwm.write("	</head>\r\n");
fwm.write("	<body>\r\n");
fwm.write("		<div id=\"map\"></div>\r\n");
fwm.write("		<div id=\"layer\">\r\n");


for(int i=1;i<rtc;i++){
fwm.write("			<input type=\"checkbox\" id=\"ft_"+i+"\">"+tempArray[i][0]+"<br>\r\n");
}


fwm.write("		</div>\r\n");
fwm.write("		<script>\r\n");
fwm.write("			function initMap() {\r\n");
fwm.write("				var map=new google.maps.Map(document.getElementById('map'), {\r\n");
fwm.write("					center: {lat: "+tempArray[0][0]+", lng: "+tempArray[0][1]+"},\r\n");
fwm.write("					zoom: "+tempArray[0][2]+"\r\n");
fwm.write("				});\r\n");
fwm.write("\r\n");

for(int i=1;i<rtc;i++){
fwm.write("				var ft_layer_"+i+"=new google.maps.FusionTablesLayer({\r\n");
fwm.write("					query: {\r\n");
fwm.write("						from: '"+tempArray[i][1]+"'\r\n");
//fwm.write("					}, options: {styleId: "+tempArray[i][2]+"}\r\n");
fwm.write("					}, options: {styleId: 2}\r\n");
fwm.write("				});\r\n");
fwm.write("\r\n");
}

for(int i=1;i<rtc;i++){
fwm.write("				document.getElementById('ft_"+i+"').onchange=function() {ft_layer_"+i+".setMap(document.getElementById('ft_"+i+"').checked?map:null);}\r\n");
}

fwm.write("			}\r\n");
fwm.write("		</script>\r\n");
fwm.write("		<script async defer\r\n");
fwm.write("		src=\"https://maps.googleapis.com/maps/api/js?key="+tempArray[0][3]+"&libraries=visualization&callback=initMap\">\r\n");
fwm.write("		</script>\r\n");
fwm.write("	</body>\r\n");
fwm.write("</html>\r\n");
fwm.close();







            } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("end");

    }

    public static void output(Node node) {
            
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
      	Element theBook=null, theElem=null, root=null;
        try {
FileWriter fw2=new FileWriter("saveFile.txt");
fw2.write("");
fw2.close();
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder db=factory.newDocumentBuilder();
            Document doc=db.parse(new File("D:/Papyrus/workspace/map/map.uml"));
            root=doc.getDocumentElement();
            
	    Element element=doc.getDocumentElement();
         
            NodeList nodeList=doc.getElementsByTagName("packagedElement");
        
           for(int k=0;k<nodeList.getLength();k++){
            Node fatherNode=nodeList.item(k);
     
            NamedNodeMap attributes=fatherNode.getAttributes();
         
            for(int i=0;i<attributes.getLength();i++){
                Node attribute=attributes.item(i);
                if (attribute.getNodeName()=="name"){
                	System.out.println(i+"#c_"+attribute.getNodeValue());
FileWriter fw=new FileWriter("saveFile.txt",true);
fw.write("#class:"+attribute.getNodeValue()+"\r\n");
fw.close();
         	}
                }
          
            NodeList childNodes = fatherNode.getChildNodes();
    	    
	                      
           for(int j=0;j<childNodes.getLength();j++){
                Node childNode=childNodes.item(j);
                 
 		 	
		 	NamedNodeMap attr=childNode.getAttributes();
		        if(attr!= null){      
		 	
		 	  for(int f=0;f<attr.getLength();f++){
                		Node attribute=attr.item(f);
                             if (attribute.getNodeName()=="name"){
                		System.out.println(f+"#n_"+attribute.getNodeValue());
if (f==0) {
FileWriter fw=new FileWriter("saveFile.txt",true); 
fw.write(attribute.getNodeValue()+"\r\n");
fw.close();
}
                             }
        		  }
                
                         }
		       
		 
                      NodeList childchild = childNode.getChildNodes();
                       for(int p=0;p<childchild.getLength();p++){
                         Node child=childchild.item(p);
                           if(child instanceof Element && child.getNodeName() == "defaultValue"){
                                NamedNodeMap at=child.getAttributes();
				 if(at!= null){      
 		 	
			 	   for(int f=0;f<at.getLength();f++){
                			Node attribute=at.item(f);
                           	    if (attribute.getNodeName()=="value"){
                			System.out.println(attribute.getNodeValue());
                                    }
        		           }            
                                 }
                              }
                          
                     }
                    
                  



            }

            }
      
             
            } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
}

}
