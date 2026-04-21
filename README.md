# 🏍️ IntroJakartaEE - Application Web Jakarta EE

Bienvenue! Ce projet est une **introduction complète au développement web avec Jakarta EE**. Il démontre les concepts fondamentaux pour construire une application web moderne en Java.

---

## 📚 Table des matières

1. [Qu'est-ce que Jakarta EE ?](#qu-est-ce-que-jakarta-ee-)
2. [Architecture de l'application](#architecture-de-lapplication)
3. [Concepts fondamentaux](#concepts-fondamentaux)
4. [Installation et démarrage](#installation-et-démarrage)
5. [Structure du projet](#structure-du-projet)
6. [Guide détaillé des composants](#guide-détaillé-des-composants)
7. [Les patterns utilisés](#les-patterns-utilisés)
8. [Prochaines étapes - Au-delà du CRUD](#prochaines-étapes---au-delà-du-crud)
9. [Alternatives dans l'écosystème Jakarta EE](#alternatives-dans-lécosystème-jakarta-ee)

---

## Qu'est-ce que Jakarta EE ? 🤔

Jakarta EE (anciennement Java EE) est une **spécification d'entreprise pour le développement d'applications web et distribuées en Java**. Elle fournit un ensemble de technologies standardisées pour construire des applications robustes, scalables et sécurisées.

### Pourquoi Jakarta EE ?
- ✅ **Standardisé** : Une spécification clairement définie
- ✅ **Mature** : Des années de développement et d'optimisation
- ✅ **Scalable** : Conçu pour les applications d'entreprise
- ✅ **Sécurisé** : Intégration native de sécurité
- ✅ **Flexible** : Plusieurs implémentations disponibles (TomEE, WildFly, etc.)

### Les briques fondamentales de Jakarta EE

| Technologie | Fonction |
|-------------|----------|
| **Jakarta Servlet** | Traite les requêtes HTTP |
| **Jakarta JSP** | Création de pages web dynamiques |
| **Jakarta CDI** | Injection de dépendances |
| **Jakarta Persistence (JPA)** | Accès à la base de données (ORM) |
| **Jakarta EJB** | Services métier complexes |

---

## Architecture de l'application

Ce projet suit le **pattern MVC (Model-View-Controller)** adapté aux servlets Jakarta EE:

```
┌─────────────────────────────────────────────────────────────┐
│                    CLIENT (Navigateur Web)                  │
└────────────────────────┬──────────────────────────────────┘
                         │ HTTP Request/Response
                         ▼
┌─────────────────────────────────────────────────────────────┐
│                    LAYER PRÉSENTATION                        │
│  ┌─────────────────────────────────────────────────────┐   │
│  │ JSP Pages (views) - HTML généré dynamiquement      │   │
│  │ - index.jsp (liste des motos)                      │   │
│  │ - login.jsp, register.jsp (authentification)       │   │
│  │ - details.jsp, create.jsp, update.jsp (CRUD)      │   │
│  └─────────────────────────────────────────────────────┘   │
└────────────────────────┬──────────────────────────────────┘
                         │
┌─────────────────────────────────────────────────────────────┐
│                    LAYER CONTRÔLE                            │
│  ┌─────────────────────────────────────────────────────┐   │
│  │ Servlets & Filtres - Orchestration de la logique   │   │
│  │ - BikeServlet.java (affiche les motos)             │   │
│  │ - BikeCreateServlet.java (création)                │   │
│  │ - LoginServlet.java (authentification)             │   │
│  │ - AuthFilter.java (gestion des droits d'accès)    │   │
│  └─────────────────────────────────────────────────────┘   │
└────────────────────────┬──────────────────────────────────┘
                         │
┌─────────────────────────────────────────────────────────────┐
│                    LAYER MÉTIER                              │
│  ┌─────────────────────────────────────────────────────┐   │
│  │ DAOs & Models - Logique métier et données          │   │
│  │ - BikeDao, UserDao (interfaces)                    │   │
│  │ - BikePostgresDao, UserPostgresDao (impl)          │   │
│  │ - Bike.java, User.java (entités)                  │   │
│  └─────────────────────────────────────────────────────┘   │
└────────────────────────┬──────────────────────────────────┘
                         │
┌─────────────────────────────────────────────────────────────┐
│                    LAYER DONNÉES                             │
│  PostgreSQL Database - Persistance des données               │
│  - Table users (email, password, role)                      │
│  - Table bikes (brand, model, horse_power, image_url)      │
└─────────────────────────────────────────────────────────────┘
```

---

## Concepts fondamentaux

### 1️⃣ **Les Servlets** - Le cœur des web apps Jakarta EE

Une servlet est une **classe Java qui traite les requêtes HTTP** et génère des réponses.

```java
@WebServlet(value = "/bike")
public class BikeServlet extends HttpServlet {
    @Inject
    private BikeDao bikeDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // 1. Récupère les données
        req.setAttribute("bikes", bikeDao.findAll());
        
        // 2. Transfère au template JSP
        req.getRequestDispatcher("/pages/bike/index.jsp").forward(req, resp);
    }
}
```

**Points clés:**
- `@WebServlet(value = "/bike")` : Mappe l'URL `/bike` à cette servlet
- `doGet()` : Traite les requêtes GET
- `doPost()` : Traite les requêtes POST
- `req.setAttribute()` : Passe des données à la JSP
- `forward()` : Transmet au template JSP

### 2️⃣ **L'injection de dépendances (CDI)**

Jakarta CDI automatise la gestion des dépendances:

```java
@ApplicationScoped  // La classe est créée une seule fois
public class BikePostgresDao implements BikeDao {
    // Implémentation du DAO
}

// Dans la servlet:
@Inject  // CDI injecte automatiquement une instance
private BikeDao bikeDao;
```

**Avantages:**
- Pas de `new BikePostgresDao()` manuel
- Gestion automatique du cycle de vie
- Facile à tester et à remplacer

### 3️⃣ **Les Filtres** - Intercepteurs de requêtes

Les filtres interceptent les requêtes **avant** qu'elles n'atteignent les servlets:

```java
@WebFilter(urlPatterns = "/*")  // S'applique à TOUTES les requêtes
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, 
                        FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpReq = (HttpServletRequest) request;
        User user = (User) httpReq.getSession().getAttribute("user");
        
        String path = httpReq.getRequestURI();
        
        // Routes publiques - tout le monde passe
        if (isPublicRoute(path)) {
            chain.doFilter(request, response);
            return;
        }
        
        // Routes admin - seulement les admins
        if (isAdminRoute(path) && !isAdmin(user)) {
            httpReq.getSession().setAttribute("error", "Accès refusé");
            httpResponse.sendError(403);
            return;
        }
        
        chain.doFilter(request, response);
    }
}
```

**Cas d'usage:**
- ✅ Authentification et autorisation
- ✅ Logging et audit
- ✅ Compression des réponses
- ✅ Validation des données

### 4️⃣ **Les Sessions** - Maintien de l'état utilisateur

HTTP est **stateless** (sans état), mais les sessions permettent de maintenir l'état:

```java
// Créer/Récupérer une session:
HttpSession session = req.getSession(true);

// Stocker des données:
session.setAttribute("user", userObject);

// Récupérer des données:
User user = (User) session.getAttribute("user");

// Détruire la session (logout):
session.invalidate();
```

### 5️⃣ **Le pattern DAO** - Abstraction de la persistence

Le DAO (Data Access Object) encapsule l'accès à la base de données:

```java
// Interface - contrat
public interface BikeDao {
    List<Bike> findAll();
    Bike findById(int id);
    void save(Bike bike);
    void update(Bike bike);
    void delete(int id);
}

// Implémentation - détails techniques
@ApplicationScoped
public class BikePostgresDao implements BikeDao {
    @Override
    public List<Bike> findAll() {
        // Requête SQL préparée, sécurité, error handling...
    }
}
```

**Avantages:**
- 🔄 Séparation des préoccupations
- 🔌 Facile à tester (mock le DAO)
- 🔐 Sécurité centralisée
- 💾 Flexibilité du changement de BD

### 6️⃣ **La sécurité** - Authentification et autorisation

#### Authentification (Qui êtes-vous ?)

```java
// LoginServlet
String email = req.getParameter("email");
String password = req.getParameter("password");

User user = userDao.findByEmail(email);

// Vérifier le mot de passe (JAMAIS en clair!)
if (user != null && BCrypt.checkpw(password, user.getPassword())) {
    // ✅ Authentification réussie
    req.getSession(true).setAttribute("user", user);
} else {
    // ❌ Authentification échouée
    req.setAttribute("error", "Email ou mot de passe incorrect");
}
```

#### Autorisation (Qu'avez-vous le droit de faire ?)

```java
// Dans AuthFilter
if (isAdminRoute(path)) {
    if (user == null) {
        // Pas connecté -> redirection au login
        httpResponse.sendRedirect("/auth/login");
        return;
    }
    
    if (!user.getRole().equals(UserRole.ADMIN)) {
        // Connecté mais pas admin -> erreur 403
        httpResponse.sendError(403, "Accès réservé aux administrateurs");
        return;
    }
}
```

---

## Installation et démarrage

### ✅ Prérequis

- **Java 11+** (ou 17, 21...)
- **Maven 3.6+**
- **PostgreSQL 12+**
- **Un conteneur Jakarta EE** (TomEE, WildFly, Payara...)

### 📦 Installation

1. **Cloner le projet**
```bash
git clone <URL-du-projet>
cd IntroJakartaEE
```

2. **Configurer PostgreSQL**
```sql
-- Créer la base de données
CREATE DATABASE kawasaki_db;

-- Les tables sont créées automatiquement au démarrage!
```

3. **Mettre à jour la configuration**
```java
// src/main/java/.../db/DatabaseConfig.java
private static final String DB_URL = "jdbc:postgresql://localhost:5432/kawasaki_db";
private static final String DB_USER = "postgres";
private static final String DB_PASSWORD = "votre_mot_de_passe";
```

4. **Compiler et déployer**
```bash
# Compiler le projet
mvn clean package

# Déployer sur le serveur (par exemple TomEE)
cp target/introjakartaee.war /path/to/tomee/webapps/

# Accéder à http://localhost:8080/introjakartaee
```

### 🧪 Utilisateurs de test inclus

| Email | Mot de passe | Rôle |
|-------|-------------|------|
| `admin@kawasaki.com` | `admin123` | ADMIN |
| `user@kawasaki.com` | `user123` | USER |

---

## Structure du projet

```
IntroJakartaEE/
├── src/main/
│   ├── java/be/bstorm/introjakartaee/
│   │   ├── models/              ← 📊 Les entités (données)
│   │   │   ├── Bike.java
│   │   │   ├── User.java
│   │   │   └── enums/UserRole.java
│   │   │
│   │   ├── dao/                 ← 💾 Accès à la base de données
│   │   │   ├── BikeDao.java (interface)
│   │   │   ├── BikePostgresDao.java (implémentation)
│   │   │   ├── UserDao.java (interface)
│   │   │   └── UserPostgresDao.java (implémentation)
│   │   │
│   │   ├── db/                  ← ⚙️ Configuration de la BD
│   │   │   └── DatabaseConfig.java
│   │   │
│   │   ├── filters/             ← 🔐 Filtres de sécurité
│   │   │   └── AuthFilter.java
│   │   │
│   │   └── servlets/            ← 🕸️ Contrôleurs (traitement des requêtes)
│   │       ├── auth/
│   │       │   ├── LoginServlet.java
│   │       │   ├── RegisterServlet.java
│   │       │   └── LogoutServlet.java
│   │       └── bike/
│   │           ├── BikeServlet.java (list)
│   │           ├── BikeDetailsServlet.java (read)
│   │           ├── BikeCreateServlet.java (create)
│   │           ├── BikeUpdateServlet.java (update)
│   │           └── BikeDeleteServlet.java (delete)
│   │
│   ├── resources/
│   │   ├── init_db.sql          ← Scripts d'initialisation BD
│   │   └── META-INF/beans.xml   ← Configuration CDI
│   │
│   └── webapp/
│       ├── index.jsp            ← Page d'accueil
│       ├── WEB-INF/web.xml     ← Configuration web
│       └── pages/
│           ├── auth/            ← Templates d'authentification
│           │   ├── login.jsp
│           │   └── register.jsp
│           └── bike/            ← Templates des motos
│               ├── index.jsp (list)
│               ├── details.jsp (read)
│               ├── create.jsp (create)
│               └── update.jsp (update)
│
├── pom.xml                       ← Configuration Maven
└── README.md                     ← Ce fichier!
```

---

## Guide détaillé des composants

### 🏗️ Architecture en couches

#### 1. **Models** (`models/`)
Représentent les entités métier:

```java
@EqualsAndHashCode @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
public class Bike {
    @Getter private Integer id;
    @Getter @Setter private String brand;
    @Getter @Setter private String model;
    @Getter @Setter private int horsePower;
    @Getter @Setter private String imageUrl;
}
```

#### 2. **DAOs** (`dao/`)
Encapsulent les opérations CRUD:

```java
public interface BikeDao {
    List<Bike> findAll();      // CREATE
    Bike findById(int id);      // READ
    void save(Bike bike);       // CREATE
    void update(Bike bike);     // UPDATE
    void delete(int id);        // DELETE
}
```

#### 3. **Servlets** (`servlets/`)
Traitent les requêtes HTTP:

| Servlet | Méthode | Route | Action |
|---------|---------|-------|--------|
| BikeServlet | GET | `/bike` | Affiche la liste |
| BikeDetailsServlet | GET | `/bike/details?id=X` | Affiche les détails |
| BikeCreateServlet | GET/POST | `/bike/create` | Formulaire / Création |
| BikeUpdateServlet | GET/POST | `/bike/update?id=X` | Formulaire / Modification |
| BikeDeleteServlet | POST | `/bike/delete` | Suppression |
| LoginServlet | GET/POST | `/auth/login` | Formulaire / Connexion |
| RegisterServlet | GET/POST | `/auth/register` | Formulaire / Inscription |
| LogoutServlet | GET | `/auth/logout` | Déconnexion |

#### 4. **Filtres** (`filters/`)
Interceptent et contrôlent l'accès:

```
Routes publiques    → Tout le monde ✅
Routes auth         → Non-connectés uniquement ✅
Routes admin        → ADMIN role uniquement ✅
```

#### 5. **Configuration** (`db/`)
Initialise la base de données:

- Création des tables
- Insertion des données de test
- Hashage des mots de passe avec BCrypt

---

## Les patterns utilisés

### 🎯 **MVC (Model-View-Controller)**
- **Model** : BikePostgresDao, User, Bike
- **View** : JSP pages (index.jsp, details.jsp...)
- **Controller** : BikeServlet, BikeCreateServlet...

### 🔌 **DAO (Data Access Object)**
Abstrait l'accès à la base de données pour faciliter les tests et les changements.

### 🛡️ **Filter Pattern**
Centralise la logique de sécurité et d'authentification.

### 💉 **Injection de dépendances (DI)**
Jakarta CDI gère automatiquement les instances.

### 🔄 **Session Management**
Maintien l'état utilisateur entre les requêtes.

---

## Prochaines étapes - Au-delà du CRUD

Félicitations! 🎉 Vous maîtrisez maintenant les **fondamentaux du web avec Jakarta EE** (Niveau 0 du développement web). Voici ce que vous pouvez explorer ensuite:

### 📈 **Niveau 1 - Améliorations de base**

#### 1. **Validation des données** ✨
Avant d'arriver à la base de données, validez les entrées:

```java
// Utiliser Jakarta Validation
@NotNull(message = "L'email est requis")
@Email(message = "Format d'email invalide")
private String email;

@Size(min = 6, message = "Au minimum 6 caractères")
private String password;
```

**Ressources:**
- Jakarta Bean Validation API
- Validateurs personnalisés

#### 2. **Pagination et tri** 📄
Pour les grandes listes:

```java
public class PageResult {
    private List<Bike> content;
    private long totalElements;
    private int currentPage;
    private int totalPages;
}

// Dans le DAO
public PageResult findAll(int page, int size) {
    // LIMIT et OFFSET en SQL
}
```

#### 3. **Recherche et filtrage** 🔍
```java
public List<Bike> searchByBrand(String brand) {
    // SELECT * FROM bikes WHERE brand LIKE ?
}

public List<Bike> filterByHorsePower(int minHp, int maxHp) {
    // SELECT * FROM bikes WHERE horse_power BETWEEN ? AND ?
}
```

#### 4. **Gestion des erreurs robuste** ⚠️
```java
try {
    // Logique métier
} catch (SQLException e) {
    logger.error("Erreur base de données", e);
    throw new ApplicationException("Impossible de traiter votre demande", e);
}
```

#### 5. **Logging complet** 📋
```java
import java.util.logging.Logger;

public class BikePostgresDao implements BikeDao {
    private static final Logger LOGGER = Logger.getLogger(BikePostgresDao.class.getName());
    
    public List<Bike> findAll() {
        LOGGER.info("Récupération de toutes les motos");
        // ...
    }
}
```

### 🚀 **Niveau 2 - Architecture avancée**

#### 1. **Services métier** 🎯
Ajouter une couche entre les servlets et les DAOs:

```java
@ApplicationScoped
public class BikeService {
    @Inject
    private BikeDao bikeDao;
    
    public void createBike(BikeDTO dto) {
        // Validation métier complexe
        validateBrand(dto.getBrand());
        validateHorsePower(dto.getHorsePower());
        
        // Logique métier
        Bike bike = new Bike(dto.getBrand(), ...);
        bikeDao.save(bike);
        
        // Logging
        logger.info("Moto créée: " + bike.getModel());
    }
}
```

**Avantages:**
- Séparation claire entre logique métier et persistance
- Logique métier réutilisable
- Testabilité améliorée

#### 2. **DTOs (Data Transfer Objects)** 📦
Transférez les données sans exposer les entités:

```java
public class BikeDTO {
    private String brand;
    private String model;
    private int horsePower;
    private String imageUrl;
    
    // Conversion
    public static Bike toEntity(BikeDTO dto) {
        return new Bike(dto.brand, dto.model, dto.horsePower, dto.imageUrl);
    }
}
```

#### 3. **Transactions** 🔄
Assurez la cohérence des données sur plusieurs opérations:

```java
@Transactional
public void transfertBike(int fromUserId, int toUserId, int bikeId) {
    // Soit tout réussit, soit tout échoue
    userBikeDao.removeBike(fromUserId, bikeId);
    userBikeDao.addBike(toUserId, bikeId);
}
```

#### 4. **Query Objects** 🔎
Créez des requêtes complexes réutilisables:

```java
public class BikeQuery {
    private String brand;
    private Integer minHorsePower;
    private Integer maxHorsePower;
    
    public List<Bike> execute(BikeDao dao) {
        // Construction dynamique de requêtes
    }
}
```

### 🔒 **Niveau 3 - Sécurité avancée**

#### 1. **Authentification multi-facteurs** 🔐
```java
// Ajouter 2FA avec TOTP ou SMS
public boolean verifyTwoFactor(User user, String code) {
    // Validation du code 2FA
}
```

#### 2. **HTTPS et TLS** 🔒
Chiffrer les communications

#### 3. **CSRF Protection** 🛡️
Tokens CSRF pour les formulaires

#### 4. **Rate Limiting** ⏱️
Limiter les requêtes par IP

### 📊 **Niveau 4 - Données avancées**

#### 1. **Jakarta Persistence (JPA)** 🗂️
Remplacer les requêtes SQL brutes par un ORM:

```java
@Entity
@Table(name = "bikes")
public class BikeJPA {
    @Id
    @GeneratedValue
    private Integer id;
    
    @Column(name = "brand")
    private String brand;
    
    // Relationships
    @OneToMany(mappedBy = "bike")
    private List<Rental> rentals;
}

// DAO avec JPA
public interface BikeRepository extends CrudRepository<BikeJPA, Integer> {
    List<BikeJPA> findByBrand(String brand);
}
```

**Avantages:**
- Moins de requêtes SQL
- Gestion automatique des relations
- Portabilité entre BDs

#### 2. **Requêtes complexes** 🔗
Gestion des relations (OneToMany, ManyToMany, ManyToOne)

#### 3. **Cache** ⚡
Mise en cache des résultats fréquents

### 🌐 **Niveau 5 - APIs et intégrations**

#### 1. **REST APIs** 🔌
Transformer votre app en API JSON:

```java
@WebServlet("/api/bikes")
public class BikeRestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("application/json");
        List<Bike> bikes = bikeDao.findAll();
        String json = convertToJson(bikes);
        resp.getWriter().write(json);
    }
}
```

Ou mieux, utiliser **Jakarta RESTful Web Services (JAX-RS)**:

```java
@Path("/api/bikes")
@Produces(MediaType.APPLICATION_JSON)
public class BikeResource {
    @GET
    public List<Bike> listAll() {
        return bikeDao.findAll();
    }
    
    @POST
    public Response create(Bike bike) {
        bikeDao.save(bike);
        return Response.status(201).build();
    }
}
```

#### 2. **Consumption d'APIs externes** 🌍
Intégrez des services externes

#### 3. **WebSockets** 🔌
Communication bidirectionnelle en temps réel

### 🧪 **Niveau 6 - Testing**

#### 1. **Tests unitaires** ✅
```java
public class BikeServiceTest {
    @Mock
    private BikeDao bikeDao;
    
    @InjectMocks
    private BikeService bikeService;
    
    @Test
    public void testCreateBike() {
        // Arrange
        BikeDTO dto = new BikeDTO("Kawasaki", "Ninja", 300, "url");
        
        // Act
        bikeService.createBike(dto);
        
        // Assert
        verify(bikeDao).save(any(Bike.class));
    }
}
```

#### 2. **Tests d'intégration** 🔗
Tester les servlets complètes avec des requêtes HTTP

#### 3. **Tests de performance** ⚡
Vérifier les temps de réponse

---

## Alternatives dans l'écosystème Jakarta EE

Au fur et à mesure que vous grandissez, vous aurez le choix entre plusieurs technologies dans l'écosystème Jakarta EE:

### 🎯 **Alternatives pour la gestion du contrôle (Servlets)**

#### ❌ **Jakarta Servlet** (ce que nous utilisons maintenant)
- ✅ Bas niveau, contrôle total
- ✅ Performance maximale
- ❌ Beaucoup de code répétitif
- ❌ Gestion manuelle des chemins URL

#### ✅ **Jakarta RESTful Web Services (JAX-RS)**
```java
@Path("/api/bikes")
@ApplicationScoped
public class BikeResource {
    @Inject private BikeService service;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bike> getAllBikes() {
        return service.getAllBikes();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBike(Bike bike) {
        service.save(bike);
        return Response.status(201).build();
    }
}
```

**Avantages JAX-RS:**
- 🚀 Conçu spécifiquement pour les APIs REST
- 📦 Sérialisation JSON/XML automatique
- 🔧 Content negotiation intégrée
- 📚 Meilleure documentation automatique

### 🎯 **Alternatives pour la persistance (DAOs)**

#### ❌ **JDBC brut + DAO manuel** (ce que nous utilisons)
- ✅ Contrôle total
- ✅ Performance maximale
- ❌ Lots de code SQL
- ❌ Pas d'abstraction des relations

#### ✅ **Jakarta Persistence (JPA)**
```java
@Entity
public class Bike {
    @Id
    private Integer id;
    
    @Column
    private String brand;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;
    
    @OneToMany(mappedBy = "bike")
    private List<Rental> rentals;
}

// Repository (implémentation auto par Jakarta)
public interface BikeRepository extends CrudRepository<Bike, Integer> {
    List<Bike> findByBrand(String brand);
}
```

**Avantages JPA:**
- 🔄 Gestion automatique des relations
- 📚 Lazy loading (charge les données à la demande)
- 🔐 Requêtes paramétrées (protection SQL injection)
- 🔄 Portabilité entre différentes BDs

#### ✅ **Query DSL** (construire des requêtes programmatiquement)
```java
QBike bike = QBike.bike;
List<Bike> results = queryFactory
    .select(bike)
    .from(bike)
    .where(bike.brand.eq("Kawasaki")
        .and(bike.horsePower.gt(200)))
    .fetch();
```

### 🎯 **Alternatives pour les templates (JSP)**

#### ❌ **Jakarta Server Pages (JSP)** (ce que nous utilisons)
- ✅ Intégré nativement
- ✅ Puissant et flexible
- ❌ Syntax mélange Java et HTML
- ❌ Compilation runtime

#### ✅ **Thymeleaf** (engine de templates moderne)
```html
<body>
    <h1 th:text="${'Bienvenue ' + user.name}"></h1>
    
    <table>
        <tr th:each="bike : ${bikes}">
            <td th:text="${bike.brand}">Kawasaki</td>
            <td th:text="${bike.model}">Ninja</td>
        </tr>
    </table>
</body>
```

**Avantages Thymeleaf:**
- 🎨 Syntax claire et moderne
- 🔍 Prévisualisation dans l'éditeur
- 🔌 Compatible Spring également
- 🧪 Meilleur support des tests

#### ✅ **Facelets** (autre alternative JSP)
Plus de puissance pour les composants réutilisables.

### 🎯 **Alternatives pour l'injection de dépendances (CDI)**

#### ❌ **Jakarta CDI** (ce que nous utilisons)
- ✅ Standard Jakarta EE
- ✅ Puissant
- ✅ Gestion des scopes (ApplicationScoped, RequestScoped...)

#### ✅ **Quarkus CDI**
Optimisation pour les microservices et la performance.

---

## 📚 Ressources d'apprentissage

### Officiel
- [Jakarta EE Specification](https://jakarta.ee/)
- [Apache TomEE Documentation](http://tomee.apache.org/)

### Concepts fondamentaux
- [Servlets et JSP](https://docs.oracle.com/en/java/javase/)
- [HTTP Protocol](https://developer.mozilla.org/en-US/docs/Web/HTTP)

### Frameworks alternatifs (que vous découvrirez après)
- **Spring Boot** : Alternative la plus populaire (beaucoup plus tard!)
- **Quarkus** : Optimisé pour les microservices et le cloud-native

---

## 🎓 Résumé du parcours d'apprentissage

```
Niveau 0 (Maintenant)
├─ ✅ Servlets et JSP
├─ ✅ Authentification simple
├─ ✅ CRUD de base
├─ ✅ Sessions
└─ ✅ Contrôle d'accès simple

Niveau 1 (Prochaine étape)
├─ Validation des données
├─ Pagination/Tri
├─ Recherche avancée
├─ Gestion des erreurs
└─ Logging

Niveau 2
├─ Services métier
├─ DTOs
├─ Transactions
└─ Query Objects

Niveau 3
├─ 2FA
├─ HTTPS/TLS
├─ CSRF Protection
└─ Rate Limiting

Niveau 4
├─ JPA/ORM
├─ Relations complexes
├─ Cache
└─ Optimisations BD

Niveau 5
├─ REST APIs (JAX-RS)
├─ WebSockets
├─ GraphQL
└─ Intégrations externes

Niveau 6
├─ Tests unitaires
├─ Tests d'intégration
├─ Performance testing
└─ CI/CD
```

---

## ❓ FAQ

### Q: Dois-je utiliser JAX-RS ou rester avec les Servlets?
**A:** Pour les APIs modernes, JAX-RS est meilleur. Pour les apps web traditionnelles, les Servlets + JSP suffisent. Vous pouvez les mélanger!

### Q: Quand utiliser JPA au lieu de JDBC?
**A:** JPA brille quand vous avez des relations complexes. Pour du CRUD simple, JDBC peut suffire.

### Q: Comment passer à Spring après?
**A:** Les concepts sont similaires! Spring utilise aussi:
- Servlets en dessous
- Injection de dépendances
- Repositories pour la persistance
- La connaissance de Jakarta EE vous prépare parfaitement.

### Q: Dois-je apprendre Thymeleaf maintenant?
**A:** Non, JSP suffit d'abord. Découvrez Thymeleaf quand vous voudrez des templates plus modernes.

---

## 💡 Conseils de développement

1. **Commencez simple** : Maîtrisez les Servlets avant les alternatives
2. **Testez votre code** : Les tests unitaires sont vos meilleurs amis
3. **Documentez** : Une bonne documentation aide les futurs développeurs
4. **Versionnez** : Utilisez git dès le départ
5. **Déployez régulièrement** : Ne gardez pas votre code sur votre machine
6. **Lisez le code** : Apprendre des projets existants
7. **Écoutez les erreurs** : Les logs et les exceptions vous disent ce qui ne va pas

---

## 📝 Conclusion

Vous avez maintenant une **solide base en développement web avec Jakarta EE**! 🎉

Ce projet vous a montré:
- ✅ Comment les servlets traitent les requêtes HTTP
- ✅ Comment les DAOs gèrent la persistance
- ✅ Comment les filtres contrôlent l'accès
- ✅ Comment les sessions maintiennent l'état
- ✅ Comment organiser une application web scalable

Le monde du développement web est vaste, mais vous avez les bonnes fondations pour continuer votre apprentissage.

**Prochaines étapes recommandées:**
1. Améliorez ce projet avec la validation (Niveau 1)
2. Refactorisez en ajouter une couche Services (Niveau 2)
3. Migrez vers JPA pour une meilleure expérience (Niveau 4)
4. Créez une API REST (Niveau 5)
5. Ajoutez des tests complets (Niveau 6)

**Bonne chance et heureux codage! 🚀**

---

*Maintenu par l'équipe Bya Sébastien*  
*Dernière mise à jour: 2026*

