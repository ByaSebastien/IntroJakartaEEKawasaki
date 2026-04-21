# 🔐 Règles d'Accès AuthFilter

## Vue d'ensemble
Le filtre `AuthFilter` contrôle l'accès aux différentes routes de l'application selon le statut de connexion et le rôle de l'utilisateur.

---

## 📋 Règles d'Accès

### ✅ Routes Publiques (Accessibles à TOUS)
```
/ (Accueil)
/bike (Liste des motos)
/bike/details?id=X (Détails d'une moto)
/resources/* (Fichiers statiques)
*.css, *.js, *.jpg, *.png, *.gif
```

### 🔐 Routes d'Authentification (NON-CONNECTÉS UNIQUEMENT)
```
/auth/login (Connexion)
/auth/register (Inscription)
```
**Comportement** : Si un utilisateur connecté accède à ces routes, il est redirigé vers `/bike`

### 🛡️ Routes Administrateur (ADMIN UNIQUEMENT)
```
/bike/create (Créer une moto)
/bike/update (Modifier une moto)
/bike/delete (Supprimer une moto)
```
**Comportement** :
- Si non-connecté → Redirection vers `/auth/login`
- Si connecté mais pas ADMIN → Erreur 403 (Accès Refusé)
- Si ADMIN → Accès autorisé

---

## 👥 Rôles

| Rôle | Création | Modification | Suppression | Login/Register | Consultation |
|------|----------|--------------|-------------|----------------|--------------|
| **ADMIN** | ✅ | ✅ | ✅ | ❌ | ✅ |
| **USER** | ❌ | ❌ | ❌ | ❌ | ✅ |
| **Anonyme** | ❌ | ❌ | ❌ | ✅ | ✅ |

---

## 🚀 Identifiants de Test

**Admin** (accès complet)
- Email: `admin@kawasaki.com`
- Mot de passe: `admin123`

**User** (lecture seule)
- Email: `user@kawasaki.com`
- Mot de passe: `user123`

