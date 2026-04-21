package be.bstorm.introjakartaee.models.enums;

/**
 * Énumération définissant les rôles disponibles pour les utilisateurs du système.
 *
 * Les deux rôles sont :
 * - ADMIN : Administrateur avec accès complet aux fonctionnalités de gestion
 * - USER : Utilisateur standard avec accès en lecture seule
 *
 * @author IntroJakartaEE
 * @version 1.0
 */
public enum UserRole {
    /**
     * Rôle administrateur avec tous les privilèges d'accès.
     */
    ADMIN,
    /**
     * Rôle utilisateur standard avec accès limité.
     */
    USER
}
