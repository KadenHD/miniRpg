package promeo.sio.rpg.entity;

public class Personnage {

    private String nom;
    private int sante;
    private int puissance;
    private int defense;
    private int magie;
    private int barriere;
    private int mana;

    public String getNom() {
        return nom;
    }

    public int getSante() {
        return sante;
    }

    public int getPuissance() {
        return puissance;
    }

    public int getDefense() {
        return defense;
    }

    public int getMagie() {
        return magie;
    }

    public int getBarriere() {
        return barriere;
    }

    public int getMana() {
        return mana;
    }

    public Personnage(String nom, int sante, int puissance, int defense, int magie, int barriere, int mana) {
        this.nom = nom;
        this.sante = sante;
        this.puissance = puissance;
        this.defense = defense;
        this.barriere = barriere;
        this.magie = magie;
        this.mana = mana;
    }

    public void attaque(Personnage cible){
        int de = 1 + (int)(Math.random() * ((6 - 1) + 1));
        int deDef = 1 + (int)(Math.random() * ((6 - 1) + 1));

        int degats = 0;
        if(de*puissance > deDef* cible.defense){
            degats = de*puissance - deDef* cible.defense;
        }

        cible.sante = cible.sante - degats;

        System.out.println(nom+" attaque "+cible.nom+" et lui inflige "+degats+" dégâts.");
    }

    public void sort(Personnage cible){
        int de = 1 + (int) (Math.random() * ((6-1) +1)*1.2);
        int deBar =  1 + (int) (Math.random() * ((6-1) +1));

        int degats = 0;
        if(de*magie > deBar* cible.barriere){
            degats = de*magie - deBar* cible.magie;
        }

        cible.sante = cible.sante - degats;

        System.out.println(nom+" lance un sortilège à "+cible.nom+" et lui inflige "+degats+" dégâts.");
    }
}
