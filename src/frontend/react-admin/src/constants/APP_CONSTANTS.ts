export const SEXE: { name: string, value: string }[] = [
    { name: "Choisir..", value: "null" },
    { name: "Masculin", value: "masculin" },
    { name: "Féminin", value: "féminin" },
]

export const UPLOAD_IMAGE = "UPLOAD_IMAGE";

export enum MODAL_MODE {
    create = "CREATE",
    update = "UPDATE",
    view = "VIEW",
}

export enum TYPE_VISITE {
    ordinaire = "ordinaire",
    rendez_vous = "rendez-vous"
}

export enum STATUS_RDV {
    draft = "En Attente",
    pendind = "En Cours",
    passed = "Passe"
}

export interface Time {
    hour: number,
    min: number, 
}