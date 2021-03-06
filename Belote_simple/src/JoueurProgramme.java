/**
 *  @class JoueurProgramme
 *  @author lacertus, Nathan
 *  @resume represente l'IA d'un joueur de belote
 * 
 * */

public class JoueurProgramme
{
	
	private Carte monPaquet[]= new Carte [8];
	private int recoitval = 0;
	private String choixatout=new String("");
	private Carte cartejoue; 
	private boolean belote;
	
	
	//====================================================
	public void affichejoueur(int x){
		for (int i=0; i<Fenetre.nbcartej[x]; i++) {
			Terminal.ecrireStringln("valeur jeux joueur "+x+" carte n "+i+" valeur "+this.monPaquet[i].getFigure().getNom()+" "+this.monPaquet[i].getCouleur().getNom());
		}
		Terminal.ecrireStringln("-----------------------");
	}
	
	
	//==========================================================
	// recoit la carte sur le paquet
	public int recoit(Carte[] tabCards,int y,Carte u){
		this.monPaquet[y]=tabCards[0];
		y++;
		for (int v=0 ; v<32; v++){
			if (v+1<32){
				tabCards[v] = tabCards[v+1];
			}else{
				tabCards[v]= u;
			} 	
		}
	  	return y;
	}
	
	
	//========================================================
	// prend premier tour
	public void prendpremier(int numeroJoueur,Carte carteRetournee,int nbcarte){
		String rep,rep2="o",coulatout;
		boolean recup, hasValet=false, hasNeuf=false;
		int totalpoint=0, nbAtout =0;
		
		switch(numeroJoueur){
			case 0 :
				Terminal.ecrireStringln("Vous voulez prendre aux premier tour ? (o,n)");
				rep=Terminal.lireString();
				recup=rep.equals(rep2);
				if (recup == true){
					this.recoitval=1;
					//il a pris donc recoitval=1
				}else{
					this.recoitval=0;	
				}
				break;
			default : 
				for (int i=0 ; i<nbcarte; i++){
					
					/*on regarde le nombre d'atout qu'il possede et s'il a le valet et 9*/
					if(this.monPaquet[i].getCouleur().getNom() == carteRetournee.getCouleur().getNom()){
						if(this.monPaquet[i].getFigure().getNom().equals("Valet") ||  carteRetournee.getFigure().getNom().equals("Valet")){
							hasValet=true;
						}
						 if( this.monPaquet[i].getFigure().getNom().equals("Neuf") ||  carteRetournee.getFigure().getNom().equals("Neuf")){
							 hasNeuf=true;
						 }
						nbAtout++;
					}
					totalpoint+=Arbitre.Points(this.monPaquet[i],carteRetournee.getCouleur().getNom());
				}

				//ajout des points de la carte du milieu
				totalpoint+=Arbitre.Points(carteRetournee,carteRetournee.getCouleur().getNom());
				//on ajoute la carte retournee a notre nombre d'atouts
				nbAtout++;
				/*
				 *  on prend si : 
				 * 		- on a 40 pts et moins de 4 atouts avec le valet et le neuf
				 *  	- on a 40pts et plus de 3 atouts avec le valet 
				 *     - on a 40pts et plus de 5 atouts
				 * */
				Terminal.ecrireStringln("Joueur "+numeroJoueur+" : ");
				Terminal.ecrireStringln("Nombre d'atouts : "+nbAtout+", Valet : "+hasValet+" Neuf : "+ hasNeuf+", points : "+totalpoint);
				if ( totalpoint>=40 && ( (nbAtout < 4 && hasValet && hasNeuf )
						|| ( nbAtout >3 && nbAtout < 5 &&  hasValet) || (nbAtout >=5 ))){
					Terminal.ecrireStringln("Jeu du joueur "+numeroJoueur+" qui a pris : ");
					for (int i = 0; i < 7; i++) {
						Terminal.ecrireStringln("carte : "+monPaquet[i]);
					}
					this.recoitval=1;
				}else{
					this.recoitval=0;
				}
				//affiche la valeur de toute les cartes
				//Terminal.ecrireStringln("Valeur des cartes "+totalpoint);
				break;
		}
		
	}
	
	
	//====================================================
	// gestion pour prendre au deuxieme tour
	public void prenddeuxieme(int numeroJoueur,Carte carteRetournee,int nbcarte){
		String rep, rep2="o",atoutret="";
		String[] coulatout={"Coeur","Pique","Trefle","Carreau"};
		boolean recup, hasValetCouleurCourante=false, hasNeufCouleurCourante=false;
		boolean hasValetCouleurPrecedente = false,hasNeufCouleurPrecedente=false;
		int totalpoint=0,pointInt=0, nbAtoutCouleurPrecedente =0,nbAtoutCouleurCourante=0;

		Terminal.ecrireStringln("Deuxieme tour : joueur "+numeroJoueur+" reflechi ...");
		// En fonction du joueur
		switch(numeroJoueur){
			case 0 :
				Terminal.ecrireStringln("Vous voulez prendre aux Deuxi�me tour ? (o,n)");
				rep=Terminal.lireString();
				recup=rep.equals(rep2);
				if (recup == true){
					Terminal.ecrireStringln("Vous voulez prendre a quel couleur ? (Coeur,Pique,Trefle,Carreau)");
					rep=Terminal.lireString();
					this.recoitval=1;
					rep2="Coeur";
					recup=rep.equals(rep2);
					if (recup == true){
						this.choixatout=rep2;
					}
					rep2="Carreau";
					recup=rep.equals(rep2);
					if (recup == true){
						this.choixatout=rep2;
					}
					rep2="Trefle";
					recup=rep.equals(rep2);
					if (recup == true){
						this.choixatout=rep2;
					}
					rep2="Pique";
					recup=rep.equals(rep2);
					if (recup == true){
						this.choixatout=rep2;
					}
				}else{
					this.recoitval=0;	
				}
				break;
			default :
				// boucle de teste sur les 4 getCouleur()s pour l'ordinateur
				for(int i=0 ; i<4; i++){	
					totalpoint=0;
					nbAtoutCouleurPrecedente = nbAtoutCouleurCourante;
					nbAtoutCouleurCourante = 0;
					hasValetCouleurPrecedente = hasValetCouleurCourante;
					hasNeufCouleurPrecedente =hasNeufCouleurCourante;
					hasValetCouleurCourante = false;
					hasNeufCouleurCourante = false;
					
					for (int j=0 ; j<nbcarte; j++){
						//on regarde les atouts
						if(this.monPaquet[j].getCouleur().getNom() == coulatout[i]){
							if(this.monPaquet[j].getFigure().getNom().equals("Valet")){
								hasValetCouleurCourante=true;
							}
							if( this.monPaquet[j].getFigure().getNom().equals("Neuf") ){
								 hasNeufCouleurCourante=true;
							 }
							 nbAtoutCouleurCourante++;
						}
						totalpoint +=Arbitre.Points(this.monPaquet[j],coulatout[i]);
					}
					//ajout des points de la carte du milieu
					totalpoint+=Arbitre.Points(carteRetournee,coulatout[i]);
					
					if (pointInt<totalpoint && 
					    ( (nbAtoutCouleurCourante < 4 && hasValetCouleurCourante && hasNeufCouleurCourante ) 
						|| ( nbAtoutCouleurCourante >3 && nbAtoutCouleurCourante < 5  && hasValetCouleurCourante) 
						|| (nbAtoutCouleurCourante >=5 )))	{
						pointInt=totalpoint;
						atoutret=coulatout[i];
					}
					
					Terminal.ecrireStringln("Joueur "+numeroJoueur+" pour couleur "+coulatout[i]+ " : ");
					Terminal.ecrireStringln("Nombre d'atouts : "+nbAtoutCouleurCourante+", Valet : "+hasValetCouleurCourante
							+" Neuf : "+ hasNeufCouleurCourante+", points : "+totalpoint);
				}
			//test pour la prise
			if (pointInt>=40){
				this.recoitval=1;
				this.choixatout=atoutret;
				for (int i = 0; i < 7; i++) {
					System.out.println("carte : "+monPaquet[i]);
				}
				
				Terminal.ecrireStringln("Le joueur N"+numeroJoueur+" a pris a la couleur "+atoutret);	
			}else{
				this.recoitval=0;
			}		
			break;				
		}
	}
	
	
	//================================
	//Trie le jeux de carte
	public void trijeux(String coulatout){
		int respos;
		int pos=0;
		int val1;
		int val3;
		boolean recup;
		Carte Paquettampon1[]= new Carte [8];

		String[]couleurtri={"Coeur","Trefle","Carreau","Pique"};
		for (int x= 0 ; x<4 ; x++){
			respos=0;
			for (int y = 0 ; y<8 ;y++){	
				recup=couleurtri[x].equals(this.monPaquet[y].getCouleur().getNom());
				if (recup == true){
					Paquettampon1[pos]=this.monPaquet[y];
					pos++;
				}
			}
		}
		for (int y = 0 ; y<8 ;y++){
			this.monPaquet[y]=Paquettampon1[y];
		}

	}
	
	
	//======================================
	//joue premier 
	public Carte jouepremier(int numeroJoueur,Carte card,String carteret,String coulatout){
		
		
		/*AFFICHAGE*/
		System.out.println("Methode jouepremier : \nParametres :");
		System.out.println(" carte : "+card+", couleur demandee : "+carteret+", couleur atout : "+coulatout);
		System.out.println("Affichage de la main du joueur "+numeroJoueur);
		for (int i = 0; i < monPaquet.length; i++) {
			System.out.println(monPaquet[i]);
		}
		/*FIN AFFICHAGE*/
		
		Carte renvoijouee=this.monPaquet[0];
		int rep;
		boolean test;
		switch(numeroJoueur){
			case 0 :
				int n=20;
				while (n!=0){
					Terminal.ecrireStringln("Quel Numero de carte jouez vous?");
					try {
						rep=Terminal.lireInt();
						rep--;
						if (rep>=0 && rep<=7){
							renvoijouee=this.monPaquet[rep];
							test=Arbitre.testcartejouee(this.monPaquet,carteret,renvoijouee,coulatout);
							if(test==true){
								for (int v = rep ; v < 8 ;v++){
									if (v+1<8){
										this.monPaquet[v]=this.monPaquet[v+1];
									}else{
										this.monPaquet[v]=card;
									}
								}
								n=0;
							}
						}
					}catch (TerminalException e){
						Terminal.ecrireStringln("erreur de saisie");
					}
				}
				break;
			default :
				rep=Arbitre.testcartejouee2(this.monPaquet,carteret,coulatout);
				Terminal.ecrireStringln("Le joueur choisi de jouer la carte n�" + rep+" : " + this.monPaquet[rep]);
				renvoijouee=this.monPaquet[rep];
				
				for (int i =rep ; i < 8 ;i++){
					if (i+1<8){
						this.monPaquet[i]=this.monPaquet[i+1];
					}else{
						this.monPaquet[i]=card;
					}

				}
				break;
		}
		return renvoijouee;
	}

	public void afficherMain()
	{
		Terminal.ecrireStringln("Affichage main : ");
		for (int i = 0; i < monPaquet.length; i++) {
			Terminal.ecrireStringln(monPaquet[i].toString());
		}
	}
	
	

	public Carte[] getMonPaquet() {
		return monPaquet;
	}


	public int getRecoitval() {
		return recoitval;
	}


	public String getChoixatout() {
		return choixatout;
	}
}