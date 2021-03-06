/**
 *  @class Arbitre
 *  @author lacertus, Nathan
 *  @resume classe représentant l'arbitre, il choisit le donneur, compte les points ...
 * */

public class Arbitre
{
	
	
	public static int quidonne(int donne){
		//affiche avant le traitement qui doit donner le jeux
		Terminal.ecrireStringln("qui donne dans arbitre J"+donne);
		switch(donne){
			case 0 : donne = 1;
				break;
			case 1 : donne = 2;
				break;
			case 2 : donne = 3;
				break;
			case 3 : donne = 0;
				break;
		}
		//affiche la personne qui doit donner le jeux
		Terminal.ecrireStringln("qui donne dans arbitre J"+donne);
		return donne;
	}
	
	
	//======================================
	// figure d'une carte
	public static int fig2(Carte x){
		int vaaff=0;
		if (x.getFigure()==Figure.Sept){
			vaaff=1;
		}
		if (x.getFigure()==Figure.Huit){
			vaaff=2;
		}
		if (x.getFigure()==Figure.Neuf){
			vaaff=3;
		}
		if (x.getFigure()==Figure.Dix){
			vaaff=4;
		}
		if (x.getFigure()==Figure.Valet){
			vaaff=5;
		}
		if (x.getFigure()==Figure.Dame){
			vaaff=6;
		}
		if (x.getFigure()==Figure.Roi){
			vaaff=7;
		}
		if (x.getFigure()==Figure.As){
			vaaff=8;
		}
		return vaaff;
	}
	//===================================
	// le rang d'une couleur de la carte
	public static int coulrang(Carte x){
		int vaaff2=0;
		if (x.getCouleur()==Couleur.Coeur){
			vaaff2=0;
		}
		if (x.getCouleur()==Couleur.Pique){
			vaaff2=1;
		}
		if (x.getCouleur()==Couleur.Carreau){
			vaaff2=2;
		}
		if (x.getCouleur()==Couleur.Trefle){
			vaaff2=3;
		}
		return vaaff2;		
	}
	//======================================
	// renvois la valeur d'une carte	
	public static int Points(Carte carte,String nomCouleurAtout){
		int point1=0;
		String vcouleur;
		int vfigure;
		vcouleur=carte.getCouleur().getNom();
		vfigure=fig2(carte);
		if (nomCouleurAtout==vcouleur){
			switch(vfigure){
				case 1 : point1=0;
					break;
				case 2 : point1=0;
					break;
				case 3 : point1=14;
					break;
				case 4 : point1=10;
					break;	
				case 5 : point1=20;
					break;
				case 6 : point1=3;
					break;
				case 7 : point1=4;
					break;
				case 8 : point1=11;
					break;
			}
		}else{
			switch(vfigure){
				case 1 : point1=0;
					break;
				case 2 : point1=0;
					break;
				case 3 : point1=0;
					break;
				case 4 : point1=10;
					break;	
				case 5 : point1=2;
					break;
				case 6 : point1=3;
					break;
				case 7 : point1=4;
					break;
				case 8 : point1=11;
					break;
			}
			
		}
		return point1;
	}
	//=====================================================
	//points sur une manche
	public static int Pointsjeu(Carte card,String y,String u){
		int point1=0;
		int vfigure;
		vfigure=fig2(card);
		if (y==card.getCouleur().getNom()){
			switch(vfigure){
				case 1 : point1=9;
					break;
				case 2 : point1=10;
					break;
				case 3 : point1=15;
					break;
				case 4 : point1=13;
					break;	
				case 5 : point1=16;
					break;
				case 6 : point1=11;
					break;
				case 7 : point1=12;
					break;
				case 8 : point1=14;
					break;
			}
		}else{
			
			if (u==card.getCouleur().getNom()){
				switch(vfigure){
					case 1 : point1=1;
						break;
					case 2 : point1=2;
						break;
					case 3 : point1=3;
						break;
					case 4 : point1=7;
						break;	
					case 5 : point1=4;
						break;
					case 6 : point1=5;
						break;
					case 7 : point1=6;
						break;
					case 8 : point1=8;
						break;
				}
			}else{
				
				switch(vfigure){
					case 0 :point1=0;
						break;
					case 1 : point1=0;
						break;
					case 2 : point1=0;
						break;
					case 3 : point1=0;
						break;
					case 4 : point1=0;
						break;	
					case 5 : point1=0;
						break;
					case 6 : point1=0;
						break;
					case 7 : point1=0;
						break;
					case 8 : point1=0;
						break;
				}
				
			}
		}
		return point1;
	}
	//=====================================================
	//gagne la manche
	public static boolean Gagnemanche(int[] x,int y){
		boolean v= false;
		if (y==1 || y==3){
			if(x[0]<x[1]){
				v=true;
			}else{
				v=false;
			}
		}else{
			if(x[0]>x[1]){
				v=true;
			}else{
				v=false;
			}
		}
		return v;
	}
	//====================================================
	// test fijn manche pour savoir si la partie est fini
	public static int testfinmanche(int x,int y){
		int v=0;
		if(x<y){
			v=y;	
		}else{
			y=v;
		}
		return v;
	}
	//==============================================
	//test carte jouer
	public static boolean testcartejouee(Carte[] main,String y, Carte uv,String coulatout){
		boolean v=false;
		boolean test;
		test=y.equals("");
		testc :	if(test==true){
				v=true;	
				break testc ;
			}else{
				//test si la carte est de la couleur du jeux jouer
				test=uv.getCouleur().getNom().equals(y);
			 	if(test==true){
					v=true;	
					break testc ;		
				}else{
					//test si une des cartes est de la couleur du jeux 
					for(int i=0 ; i<8 ;i++){
						test=main[i].getCouleur().getNom().equals(y);
						if(test==true){
							v=false;
							Terminal.ecrireStringln("Jouer une carte de la couleur demander "+y);
							break testc ;
						}
					}
					//Test si la carte est de la couleur de l'atout
					test=uv.getCouleur().getNom().equals(coulatout);
					if (test==true){
						v=true;
						break testc ;		
					}else{
						// test si au moins une carte est de la couleur de l'atout
						for(int i=0 ; i<8 ;i++){
							test=main[i].getCouleur().getNom().equals(coulatout);
							if(test==true){
								v=false;
								Terminal.ecrireStringln("Jouer une carte d'atout  "+coulatout);
								break testc ;
							}
						}
						v=true;
						break testc ;
					}
				}
			}
		return v;
	}
	
	/**
	 * @param une main de type Carte[], la carte retournee en string, la couleur d'atout en string
	 * @return un int => la position de la carte a jouer dans la main
	 * @resume 
	 * */
	//========================================================
	//test pour jeux nieme
	public static int testcartejouee2(Carte[] main,String carteretournee,String couleurAtout){
		int indiceCarteAJouer=0;
		Terminal.ecrireStringln("Carte demandee : "+carteretournee);
		boolean test = carteretournee.equals("");
		
		/*
		 *  A FAIRE
		 * changer la methode pour qu'elle prenne la bonne carte
		 *  	- jouer un atout si on est dans l'equipe qui a pris 
		 *  		- le valet ou un petit pour faire tomber les autre atouts si le valet n'est pas passé
		 * 		- jouer un as si pas d'atouts ou de bons atouts
		 * 		- jouer une petite carte non atout  si aucun des cas precedents
		 * */
		
		
		testc :	
			if(!test){
				for(int i=0 ; i<8 ;i++){
					test=main[i].getCouleur().getNom().equals(carteretournee);
					if(test){
						indiceCarteAJouer=i;
						break testc ;
					}	
				}
				for(int i=0 ; i<8 ;i++){
					test=main[i].getCouleur().getNom().equals(couleurAtout);
					if(test){
						indiceCarteAJouer=i;
						break testc ;
					}	
				}
				indiceCarteAJouer=0;
			}
		
		return indiceCarteAJouer;
	}
}