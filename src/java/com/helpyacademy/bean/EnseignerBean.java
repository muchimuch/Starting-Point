package com.helpyacademy.bean;

import com.helpyacademy.dao.model.Enseigner;
import com.helpyacademy.dao.model.Matiere;
import com.helpyacademy.dao.model.Niveau;
import com.helpyacademy.service.EnseignerService;
import com.helpyacademy.util.Utils;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author youssefsafi
 */
@ManagedBean
@RequestScoped
public class EnseignerBean {

    private final float PRIX_H_MIN = 10;

    private int idNiveau;
    private int idMatiere;
    private String description;
    private float prixHeure;
    private boolean cadeau20Min;
    private List<Niveau> listNiveau;
    private List<Matiere> listMatiere;
    private List<Enseigner> listMatiereEnseigner;

    private int OldidMatiere;
    private String descriptionM;
    private float prixHeureM;
    private boolean cadeau20MinM;
    
    private String niveau;
    private String matiere;
    private String cadeau;
    
    private EnseignerService enseignerService;
    private boolean success;

    public void setEnseignerService(EnseignerService enseignerService) {
        this.enseignerService = enseignerService;
    }

    public boolean isCadeau20Min() {
        return cadeau20Min;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getIdNiveau() {
        return idNiveau;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public String getDescription() {
        return description;
    }

    public float getPrixHeure() {
        return prixHeure;
    }

    public boolean getCadeau20Min() {
        return cadeau20Min;
    }

    public void setIdNiveau(int idNiveau) {
        this.idNiveau = idNiveau;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrixHeure(float prixHeure) {
        this.prixHeure = prixHeure;
    }

    public void setCadeau20Min(boolean cadeau20Min) {
        this.cadeau20Min = cadeau20Min;
    }

    public void setListMatiereEnseigner(List<Enseigner> listMatiereEnseigner) {
        this.listMatiereEnseigner = listMatiereEnseigner;
    }

    public List<Enseigner> getListMatiereEnseigner() {
        return listMatiereEnseigner;
    }

    public void setListNiveau(List<Niveau> listNiveau) {
        this.listNiveau = listNiveau;
    }

    public void setListMatiere(List<Matiere> listMatiere) {
        this.listMatiere = listMatiere;
    }

    public void setDescriptionM(String descriptionM) {
        this.descriptionM = descriptionM;
    }

    public void setPrixHeureM(float prixHeureM) {
        this.prixHeureM = prixHeureM;
    }

    public void setCadeau20MinM(boolean cadeau20MinM) {
        this.cadeau20MinM = cadeau20MinM;
    }

    public float getPRIX_H_MIN() {
        return PRIX_H_MIN;
    }

    public String getDescriptionM() {
        return descriptionM;
    }

    public float getPrixHeureM() {
        return prixHeureM;
    }

    public boolean isCadeau20MinM() {
        return cadeau20MinM;
    }

    public String getNiveau() {
        return niveau;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getCadeau() {
        return cadeau;
    }

    public void setCadeau(String cadeau) {
        this.cadeau = cadeau;
    }

    /* ------------------------------------------------------ */
    
    public void plusInfo(Enseigner enseigner){
        matiere = getMatiereNom(enseigner.getEnseignerPK().getIdMatiere());
        niveau = enseignerService.getNiveauNom(enseigner.getEnseignerPK().getIdMatiere());
        descriptionM = enseigner.getDescription();
        prixHeureM = enseigner.getPrixHeure();
        cadeau = enseigner.getCadeau20Min() == true ? "Oui":"Nom"; 
    }
    
    public String getMatiereNom(int idM){
        return enseignerService.getMatiereNom(idM);
    }
    
    public String update() {
        if (prixHeureM >= PRIX_H_MIN) {
            if (enseignerService.update(OldidMatiere,idMatiere, descriptionM, prixHeureM, cadeau20MinM)) {
                success = true;
                Utils.addMessage("La Matière a été bien modifié");
                reset();
            } else {
                success = false;
                Utils.addMessage("La Matière n'a pas été modifié");
            }
        } else {
            success = false;
            Utils.addMessage("Entrez un prix valide (min = " + PRIX_H_MIN + " DH)");
        }
        return "pretty:gestionMatiereP";
    }

    public String delete(Enseigner enseigner) {
        if (enseignerService.delete(enseigner)) {
            success = true;
            Utils.addMessage("La Matière a été bien supprimé");
        } else {
            success = false;
            Utils.addMessage("La Matière n'a pas été supprimé");
        }
        return "pretty:gestionMatiereP";
    }

    public void initUpdate(Enseigner enseigner) {
        cadeau20MinM = enseigner.getCadeau20Min();
        descriptionM = enseigner.getDescription();
        prixHeureM = enseigner.getPrixHeure();
        idMatiere = enseigner.getEnseignerPK().getIdMatiere();
        OldidMatiere = idMatiere;
        idNiveau = enseignerService.getIdNiv(idMatiere);
        listMatiere = enseignerService.MatiereParNiv(idNiveau);
    }

    public String save() {
        if (prixHeure >= PRIX_H_MIN) {
            if(enseignerService.matierExiste(idMatiere)){
                success = false;
                Utils.addMessage("La Matière du niveau indiqué existe deja . Veuillez choisir une autre Matière");
            }else if (enseignerService.add(idMatiere, description, prixHeure, cadeau20Min)) {
                success = true;
                Utils.addMessage("La Matière a été bien enregistré");
                reset();
            } else {
                success = false;
                Utils.addMessage("La Matière n'a pas été enregistré");
            }
        } else {
            success = false;
            Utils.addMessage("Entrez un prix valide (min = " + PRIX_H_MIN + " DH)");
        }
        return "pretty:gestionMatiereP";
    }

    public boolean emptyMatiereEnseigner() {
        listMatiereEnseigner = enseignerService.MatiereEnseigner();
        return listMatiereEnseigner.isEmpty();
    }

    public List<Niveau> getListNiveau() {
        if (listNiveau == null) {
            listNiveau = enseignerService.listNiveau();
        }
        return listNiveau;
    }

    public List<Matiere> getListMatiere() {
        if (listMatiere == null) {
            idNiveau = listNiveau.get(0).getId();
            listMatiere = enseignerService.MatiereParNiv(idNiveau);
        }
        return listMatiere;
    }

    public void setListMatiere(int idNiv) {
        listMatiere = enseignerService.MatiereParNiv(idNiv);
        //idMatiere = listMatiere.get(0).getId();
    }

    public void updateListMatiere(ValueChangeEvent event) {
        idNiveau = (int) event.getNewValue();
        listMatiere = enseignerService.MatiereParNiv(idNiveau);
    }

    public void reset() {
        description = null;
        prixHeure = 0;
        cadeau20Min = false;
    }
}
