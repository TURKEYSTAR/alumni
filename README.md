# 🎓 Plateforme de Gestion des Alumni ESMT

Application Spring Boot pour la gestion et le suivi des anciens étudiants de l'ESMT (École Supérieure Multinationale des Télécommunications).

## 📋 Fonctionnalités

### Espace Administrateur
- 📊 **Dashboard avec statistiques** : Graphiques interactifs (Chart.js)
    - Nombre total d'alumni
    - Répartition par promotion
    - Évolution par année de soutenance
    - Statistiques de situation professionnelle
- 👥 **Gestion complète des alumni** : CRUD (Create, Read, Update, Delete)
- 🔍 **Vue détaillée** de chaque alumni
- ➕ **Création automatique de comptes** pour les alumni

### Espace Alumni
- 👤 **Profil personnel** avec toutes les informations
- ✏️ **Modification du profil** (informations professionnelles)
- 📱 **Interface responsive** et moderne

## 🔐 Système d'authentification

### Connexion Alumni
- **Login** : Matricule (ex: ALU2020001)
- **Mot de passe** : Promotion (ex: 2020)

### Connexion Admin
- **Login** : admin
- **Mot de passe** : admin123

### OAuth2 (Optionnel)
- Connexion avec Google (nécessite configuration)

## 🛠️ Technologies utilisées

- **Backend** : Spring Boot 3.x
- **Sécurité** : Spring Security 6
- **Base de données** : H2 (dev), MySQL/PostgreSQL (prod)
- **ORM** : JPA/Hibernate
- **Template** : Thymeleaf
- **Frontend** : Bootstrap 5, Bootstrap Icons, Chart.js
- **Build** : Maven

## 📦 Structure du projet

```
src/main/java/sn/esmt/tpgestionalumni/
├── config/              # Configuration Spring Security
├── controller/          # Contrôleurs (Admin, Alumni)
├── model/              # Entités (Alumni, User, Role)
├── repository/         # Repositories JPA
└── service/            # Services métier

src/main/resources/
├── templates/
│   ├── admin/          # Pages admin (dashboard, liste)
│   ├── alumni/         # Pages alumni (profil)
│   ├── fragments/      # Fragments réutilisables
│   └── error/          # Pages d'erreur
└── application.properties
```

## 🚀 Installation et démarrage

### Prérequis
- Java 17 ou supérieur
- Maven 3.6+
- (Optionnel) MySQL ou PostgreSQL

### Étapes

1. **Cloner le projet**
```bash
git clone [URL_DU_REPO]
cd tp-gestion-alumni
```

2. **Configuration de la base de données**

   Par défaut, l'application utilise H2 (base en mémoire). Pour utiliser MySQL/PostgreSQL, modifiez `application.properties`.

3. **Compiler et lancer**
```bash
mvn clean install
mvn spring-boot:run
```

4. **Accéder à l'application**
    - Application : http://localhost:8080
    - Console H2 : http://localhost:8080/h2-console
        - JDBC URL: `jdbc:h2:mem:alumnidb`
        - Username: `sa`
        - Password: `password`

## 👥 Comptes de test

L'application initialise automatiquement des données de test :

### Admin
- Login: **admin**
- Password: **admin123**

### Alumni (exemples)
| Matricule    | Promotion | Nom          |
|-------------|-----------|--------------|
| ALU2020001  | 2020      | DIOP Amadou  |
| ALU2021002  | 2021      | NDIAYE Fatou |
| ALU2022003  | 2022      | FALL Moussa  |
| ALU2023004  | 2023      | SOW Aïssatou |

## 📊 Modèle de données Alumni

```java
@Entity
public class Alumni {
    private Long id;
    private String matricule;        // Unique, utilisé comme login
    private String nom;
    private String prenom;
    private String diplomeSortie;
    private Integer anneeSoutenance;
    private String sujetMemoire;
    private String sexe;             // M/F
    private String nationalite;
    private String situationProfessionnelle; // En poste/Entrepreneur/En recherche
    private String promotion;        // Utilisé comme mot de passe
}
```

## 🔒 Sécurité

- **Authentification** : Form-based + OAuth2 (Google)
- **Autorisation** : Role-Based Access Control (RBAC)
    - `ROLE_ADMIN` : Accès complet
    - `ROLE_ALUMNI` : Accès au profil uniquement
- **Mot de passe** : Encodage BCrypt
- **CSRF** : Désactivé (demo) - À activer en production

## 🎨 Captures d'écran

### Dashboard Admin
- Statistiques en temps réel
- Graphiques interactifs (barres, ligne, donut)
- Cartes de synthèse

### Liste des Alumni
- Tableau filtrable
- Actions CRUD
- Modals pour création/édition

### Profil Alumni
- Vue complète du parcours
- Modification des informations
- Design moderne et responsive

## 📝 Améliorations possibles

- [ ] Export des données (Excel, PDF)
- [ ] Recherche avancée et filtres
- [ ] Envoi d'emails aux alumni
- [ ] Gestion des promotions
- [ ] API REST pour intégrations
- [ ] Upload de photos de profil
- [ ] Tableau de bord pour les alumni (statistiques de leur promo)
- [ ] Forum ou messagerie interne

## 🤝 Contribution

Les contributions sont les bienvenues ! N'hésitez pas à ouvrir une issue ou une pull request.

## 📄 Licence

Ce projet est sous licence MIT.

## 👨‍💻 Auteur

Développé dans le cadre d'un projet académique ESMT.

---

**Note** : Cette application est un projet de démonstration. Pour une utilisation en production, pensez à :
- Activer CSRF
- Configurer OAuth2 avec vos credentials
- Utiliser une base de données persistante
- Ajouter des tests unitaires et d'intégration
- Implémenter la gestion des erreurs avancée
- Configurer HTTPS