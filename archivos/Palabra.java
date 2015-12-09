
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.List;
//import java.util.Scanner;
import java.util.*;


public class Palabra{
	String File;
	private int TotalPalConAux=0;
	private int TotalPalSinAux=0;
	private int letraSinRepetir=0;
	Map< String,Integer> dic = new TreeMap< String,Integer>();// guarda palabras con auxiliares
	Map< String,Integer> dic2 = new TreeMap< String,Integer>();// guarda palabras sin auxiliares
	
	HashSet<String> Escape=new HashSet<String>(); //guarda palabras de escape
	
	List<Pal> sorted=new ArrayList<Pal>();     //guarda objeto de palabras ordenadamente sin auxiliares
	List<Pal> sorted2=new ArrayList<Pal>();     //guarda objeto de palabras ordenadamente sin auxiliares
	

	
	Scanner sc=null;
	
	
	public Palabra(String file){
		File=file;
	}
	
	public void mifuncion(){
		
		
			PalabrasEscape();  // agregar todas la palabras de escape y omitirlas
			
			try{
			sc = new Scanner(new File(File));
			//agregar un delimitador
			sc.useDelimiter(",|\\p{javaWhitespace}+|\\.|\\:|\\;|\\ |\\-|\\(|\\)|\\[|\\]|\\{|\\}|\"|\\'|/|\\\\|\\¿|\\?|\\!|\\¡");  // |
			
			
			
			String field;
			
			while(sc.hasNext()){
				field = sc.next().trim();
				
				field=field.toLowerCase();//conversion de Mayuscula a minuscula--->
				
				
				if(!field.equals("")){
					
					
							if(dic.containsKey(field)){// Verificar si la palabra esta en el diccionario
								int Aux=dic.get(field)+1;
								dic.put(field,Aux);
								
								//System.out.println(field);
							}else{
								dic.put(field,1);
								//letraSinRepetir++;
							}	
							//System.out.println(field);
							TotalPalConAux++;
							////// < ////////
							if(!Escape.contains(field)){//verificar palabras de escape y guardar palabras sin auxiliares
								if(dic2.containsKey(field)){// Verificar si la palabra esta en el diccionario
									int Aux=dic2.get(field)+1;
									dic2.put(field,Aux);
									
									//System.out.println(field);
								}else{
									dic2.put(field,1);
									//letraSinRepetir++;
								}
								TotalPalSinAux++;
							}
							/////// > ///////
				}
				
			}
			
			
			/////////////////////////////////////////////////////////////////////
			 
			
			System.out.println("Total de palabras: "+TotalPalConAux+"\n");
			System.out.println("Total de palabras sin auxiliares: "+TotalPalSinAux+"\n");
			
			imprirMasUtilizadas();
			
		    System.out.println("--- las 10 palabras mas comunes ----\n");
	    	for(int m=0;m<10;m++){
	    		System.out.println(m+1+" "+sorted.get(m).getFrase()+" "+sorted.get(m).getValor());
	    	}
		   
			
	    	Reporte();
			
			//////////////////////////////////////////////////////////////////////	
			}catch(FileNotFoundException e){
				e.printStackTrace();
				System.out.println("El archivo txt no existe");
				
			}
			sc.close();
	}
	
	
	public void PalabrasEscape(){
		

		try{
			sc = new Scanner(new File("stopwords.txt"));
			
			String field;
		
			while(sc.hasNext()){
				
				field = sc.next().trim();
				
				field=field.toLowerCase();//conversion de Mayuscula a minuscula--->
				
				if(!field.equals("")){
						Escape.add(field);
				}
			
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
			System.out.println("El archivo data.txt no existe");
			
		}
		sc.close();
		//////////////////////////////////////
		//System.out.println("\nPalabras de escape\n");
		//System.out.println(Escape);
	   	//for(int m=0;m<Escape.size();m++){
    	//	System.out.println(m+1+" "+Escape.);
    		
    	//}
		
	}
	
	/*public void imprimir(){
	    String clave;
	    int i=1;
	    Iterator<String> aux = dic.keySet().iterator();
	    System.out.println("-- Lista de palabras ---");
	    while(aux.hasNext()){
	        clave = aux.next();
	        System.out.println(i+" "+clave + " = " + dic.get(clave));
	        i++;
	    }   
	}*/
	
	public void imprirMasUtilizadas(){
		
	    int r=0;
	    
	    for(String w:dic.keySet()){
	    	Integer c=dic.get(w);
	    	Pal e=new Pal(w,c);
	    	
	    	if(sorted.isEmpty()== true){
	    		sorted.add(0,e);
	    	}
	    	
	    	if(sorted.isEmpty()== false){
		    	//System.out.println(w+" "+dic.get(w));
		    	//System.out.println(sorted.get(e.getValor()));
	    		
	    		
		    	for(int m=0;m<sorted.size();m++){
		    		if(sorted.get(m).getValor() < c){
		    			
		    			sorted.add(m,e);
		    			break;
		    		}else if(sorted.size()==m+1){
		    			
		    			sorted.add(m,e);
		    			break;
		    		}
		    		
		    		
		    	}
	    	}
	    	r++;
	    }
	}
	
	
	public void Reporte(){
		
		BufferedWriter bw=null;
		
		try{
			bw=new BufferedWriter(new FileWriter("Reporte.txt"));
/////////////////////////////////////////////////////////////////////////////////////////////
			/*
			System.out.println("Total de palabras: "+TotalPalConAux+"\n");
			System.out.println("Total de palabras sin auxiliares: "+TotalPalSinAux+"\n");
			
			imprirMasUtilizadas();
			
		    System.out.println("--- las 10 palabras mas comunes ----\n");
	    	for(int m=0;m<10;m++){
	    		System.out.println(m+1+" "+sorted.get(m).getFrase()+" "+sorted.get(m).getValor());
	    	}
		   
			
	    
	    	*/
	    	bw.write("Total de palabras: "+TotalPalConAux+"\r\n\r\n");
	    	
	    	bw.write("Total de palabras sin auxiliares: "+TotalPalSinAux+"\r\n\r\n");
	    	
	    	bw.write("--- las 10 palabras mas comunes ----\r\n\r\n");
	    	for(int m=0;m<10;m++){
	    		bw.write(m+1+".- "+sorted.get(m).getFrase()+" "+sorted.get(m).getValor()+"\r\n");
	    	}
	    	
	    	bw.write("\r\n\r\n--- Conteo de palabras de mayor a menor ----\r\n\r\n");
	    	
	    	for(int m=0;m<sorted.size()-1;m++){
	    		bw.write(m+1+".- "+sorted.get(m).getFrase()+" "+sorted.get(m).getValor()+"\r\n");
	    	}
			
			
/////////////////////////////////////////////////////////////////////////////////////////////
			//bw.write("hola"+"\n");
			
		}catch(FileNotFoundException e1){
			e1.printStackTrace();
			System.out.println("No se pudo crear el archivo\n");
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(bw!=null)
				try{
					bw.close();
				}catch(IOException e){
					e.printStackTrace();
				}
		}
		
	}
	

	

	
	public static void main(String[] args) {
		
		
		Palabra prueba=new Palabra("file.txt");
		
		//prueba.PalabrasEscape(); // agregar elementos en mi hash  set
		 prueba.mifuncion();      //contador de palabras
		//prueba.imprirMasUtilizadas();  //
		
	}

}

/////////// clase nueva para guardar la palabra y el numero de vecez repetido
 class Pal {
	private String Frase;
	private int Valor;
	
	public Pal(String frase,int valor ){
		Frase=frase;
		Valor=valor;
	}
	
	public String getFrase() {
		return Frase;
	}

	public void setFrase(String frase) {
		Frase = frase;
	}

	public int getValor() {
		return Valor;
	}

	public void setValor(int valor) {
		Valor = valor;
	}
}
