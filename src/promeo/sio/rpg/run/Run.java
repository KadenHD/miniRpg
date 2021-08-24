package promeo.sio.rpg.run;

import org.w3c.dom.ls.LSOutput;
import promeo.sio.rpg.entity.Personnage;
import promeo.sio.rpg.colors.Colors;

import java.util.LinkedList;
import java.util.Scanner;

public class Run {

    private static LinkedList<Personnage> mob;

    public static void main (String[] args){
        Scanner saisie = new Scanner(System.in);  // Create a Scanner object
        String invocatrice = Colors.PURPLE_BOLD_BRIGHT+"Invocatrice"+Colors.RESET;
        System.out.println(invocatrice +" : Quel est votre nom "+Colors.YELLOW_BOLD_BRIGHT+"Héros"+Colors.RESET+ " ?");

        String nomJoueur = saisie.nextLine();  // Read user input

        Personnage joueur = new Personnage(Colors.YELLOW_BOLD_BRIGHT+nomJoueur+Colors.RESET, 30, 12, 11, 10, 8, 5);

        mob = new LinkedList<>();
        mob.add(new Personnage( Colors.BLUE_BOLD_BRIGHT+"Skaven"+Colors.RESET, 20, 9, 6, 0, 0, 0));
        mob.add(new Personnage( Colors.BLUE_BOLD_BRIGHT+"Ours"+Colors.RESET, 20, 9, 6, 0, 0, 0));
        mob.add(new Personnage( Colors.BLUE_BOLD_BRIGHT+"Drake"+Colors.RESET, 20, 9, 6, 0, 0, 0));
        mob.add(new Personnage( Colors.BLUE_BOLD_BRIGHT+"Kobold"+Colors.RESET, 20, 9, 6, 0, 0, 0));

        int aventure = 0;
        while(aventure != 1 && aventure != 2) {
            Scanner commencer = new Scanner(System.in);  // Create a Scanner object
            System.out.println(invocatrice+" : Très bien "+joueur.getNom()+", Voulez vous commencer votre "+Colors.WHITE_BOLD_BRIGHT+"aventure"+Colors.RESET+" ?");  // Output user input
            System.out.println("1 - "+Colors.GREEN_BOLD_BRIGHT+"Oui"+Colors.RESET);
            System.out.println("2 - "+Colors.RED_BOLD_BRIGHT+"Non"+Colors.RESET);
            aventure = commencer.nextInt();  // Read user input
        }

        while (aventure == 1){
            System.out.println(Colors.WHITE_BOLD_BRIGHT+"<---------------------------COMBAT------------------------->"+Colors.RESET);

            Personnage ennemi = mob.get((int)(Math.random()*((mob.size()-1)+1)));
            Personnage tempEnnemi = new Personnage(ennemi.getNom(),ennemi.getSante(),ennemi.getPuissance(),ennemi.getDefense(),ennemi.getMagie(),ennemi.getBarriere(),ennemi.getMana());
            System.out.println("Un "+ennemi.getNom()+" sauvage apparaît !\n");
            System.out.println(joueur.getNom()+" : Je vais te découper en rondelles !");

            int tour = 1;
            while(joueur.getSante() > 0 && ennemi.getSante() > 0){

                System.out.println(Colors.WHITE_UNDERLINED+"\nTour n°"+tour+" :"+Colors.RESET);

                Scanner choix = new Scanner(System.in);  // Create a Scanner object
                System.out.println("Que voulez vous faire pendant ce tour ?");
                System.out.println("1 - "+Colors.RED_BOLD_BRIGHT+"Attaque"+Colors.RESET);
                System.out.println("2 - "+Colors.BLUE_BOLD_BRIGHT+"Sortilège"+Colors.RESET);
                System.out.println("3 - "+Colors.GREEN_BOLD_BRIGHT+"Fuir"+Colors.RESET);

                int choose = choix.nextInt();  // Read user input

                if (joueur.getSante() > 0){
                    if (choose == 1){
                        joueur.attaque(ennemi);
                    }
                    if (choose == 2){
                        if (joueur.getMana() > 0) {
                            joueur.sort(ennemi);
                        }
                        else {
                            System.out.println("Vous essayez tant bien que mal de lancer un sort mais vous n'avez plus de "+Colors.BLUE_BOLD_BRIGHT+"Mana.\n"+Colors.RESET);
                        }
                    }
                    if(choose == 3){
                        System.out.println(Colors.WHITE_BOLD_BRIGHT+"<---------------------------FIN---------------------------->\n"+Colors.RESET);
                        System.out.println(invocatrice+" : Tu ne t'en tireras pas comme ça !!");
                        System.out.println(joueur.getNom()+Colors.RED_BOLD+" EST MORT DE SA LÂCHETÉ"+Colors.RESET);
                        return;
                    }
                    if(choose != 1 && choose != 2 && choose !=3) {
                        System.out.println(joueur.getNom()+" s'agite dans tous les sens comme un gigolo...");
                    }
                }
                if (ennemi.getSante() > 0){
                    ennemi.attaque(joueur);
                }

                tour = tour +1;
            }

            if (joueur.getSante() <= 0){
                System.out.println(Colors.WHITE_BOLD_BRIGHT+"<---------------------------FIN---------------------------->\n"+Colors.RESET);
                System.out.println(joueur.getNom()+Colors.RED_BOLD+" est mort...\n"+Colors.RESET);
                return;
            }
            else if (ennemi.getSante() <= 0){
                System.out.println(ennemi.getNom()+" a été tué !!!\n");
                System.out.println(Colors.WHITE_BOLD_BRIGHT+"<---------------------------FIN---------------------------->\n"+Colors.RESET);
                mob.remove(ennemi);
                mob.add(tempEnnemi);
            }
            aventure = 0;
            while(aventure != 1 && aventure != 2){
                Scanner continuer = new Scanner(System.in);  // Create a Scanner object
                System.out.println(invocatrice+" : Bien joué " + joueur.getNom()+", voulez vous continuer votre "+Colors.WHITE_BOLD_BRIGHT+"aventure"+Colors.RESET+" ?");  // Output user input
                System.out.println("1 - "+Colors.GREEN_BOLD_BRIGHT+"Oui"+Colors.RESET);
                System.out.println("2 - "+Colors.RED_BOLD_BRIGHT+"Non"+Colors.RESET);
                aventure = continuer.nextInt();  // Read user input
            }
        }

        if(aventure == 2){
            System.out.println(invocatrice+" : Bon bah si tu veux plus jouer avec moi je vais devoir te tuer !!");
            System.out.println(Colors.RED_BOLD+"VOUS MOURREZ DANS D'ATROCES SOUFFRANCES"+Colors.RESET);
            return;
        }

    }

}
