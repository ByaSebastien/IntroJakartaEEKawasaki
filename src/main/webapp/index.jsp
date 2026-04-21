<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kawasaki - Nos Motos Performance</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            line-height: 1.6;
            color: #333;
        }

        /* Navigation */
        nav {
            background: linear-gradient(135deg, #000000 0%, #1a1a1a 100%);
            padding: 1rem 0;
            position: sticky;
            top: 0;
            z-index: 100;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.3);
            border-bottom: 3px solid #00cc00;
        }

        nav ul {
            list-style: none;
            display: flex;
            justify-content: center;
            gap: 2rem;
            max-width: 1200px;
            margin: 0 auto;
            padding: 0 1rem;
        }

        nav a {
            color: white;
            text-decoration: none;
            font-weight: 600;
            transition: color 0.3s;
            font-size: 1.1rem;
        }

        nav a:hover {
            color: #00cc00;
        }

        /* Hero Section */
        .hero {
            background: linear-gradient(135deg, #000000 0%, #1a1a1a 50%, #00cc00 100%);
            color: white;
            padding: 8rem 2rem;
            text-align: center;
            min-height: 600px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            position: relative;
            overflow: hidden;
        }

        .hero::before {
            content: '';
            position: absolute;
            top: -50%;
            right: -10%;
            width: 500px;
            height: 500px;
            background: rgba(0, 204, 0, 0.1);
            border-radius: 50%;
            animation: float 6s ease-in-out infinite;
        }

        @keyframes float {
            0%, 100% { transform: translateY(0px); }
            50% { transform: translateY(-20px); }
        }

        .hero-content {
            position: relative;
            z-index: 2;
            max-width: 800px;
        }

        .hero h1 {
            font-size: 4rem;
            margin-bottom: 1rem;
            font-weight: 900;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
        }

        .hero p {
            font-size: 1.5rem;
            margin-bottom: 2rem;
            opacity: 0.95;
        }

        /* CTA Buttons */
        .cta-buttons {
            display: flex;
            gap: 1rem;
            justify-content: center;
            flex-wrap: wrap;
            margin-top: 2rem;
        }

        .btn {
            padding: 1rem 2rem;
            border: none;
            border-radius: 50px;
            font-size: 1.1rem;
            font-weight: 700;
            cursor: pointer;
            transition: all 0.3s ease;
            text-decoration: none;
            display: inline-block;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        .btn-primary {
            background: #00cc00;
            color: black;
            box-shadow: 0 4px 15px rgba(0, 204, 0, 0.4);
        }

        .btn-primary:hover {
            background: #00aa00;
            transform: translateY(-3px);
            box-shadow: 0 6px 20px rgba(0, 204, 0, 0.6);
        }

        .btn-secondary {
            background: white;
            color: #00cc00;
            box-shadow: 0 4px 15px rgba(255, 255, 255, 0.3);
        }

        .btn-secondary:hover {
            background: #f5f5f5;
            transform: translateY(-3px);
            box-shadow: 0 6px 20px rgba(255, 255, 255, 0.5);
        }

        /* Features Section */
        .features {
            padding: 5rem 2rem;
            background: #f9f9f9;
            max-width: 1200px;
            margin: 0 auto;
        }

        .features h2 {
            text-align: center;
            font-size: 2.5rem;
            margin-bottom: 3rem;
            color: #1a1a1a;
        }

        .features-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 2rem;
        }

        .feature-card {
            background: white;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            text-align: center;
            transition: all 0.3s;
            border-top: 4px solid #00cc00;
        }

        .feature-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
        }

        .feature-card h3 {
            color: #00cc00;
            margin-bottom: 1rem;
            font-size: 1.5rem;
        }

        .feature-card p {
            color: #666;
        }

        /* Stats Section */
        .stats {
            background: linear-gradient(135deg, #000000 0%, #1a1a1a 100%);
            color: white;
            padding: 4rem 2rem;
            text-align: center;
        }

        .stats-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 2rem;
            max-width: 1200px;
            margin: 0 auto;
        }

        .stat-item h3 {
            font-size: 2.5rem;
            color: #00cc00;
            margin-bottom: 0.5rem;
        }

        .stat-item p {
            opacity: 0.9;
        }

        /* Footer */
        footer {
            background: #000000;
            color: white;
            text-align: center;
            padding: 2rem;
            margin-top: 3rem;
            border-top: 3px solid #00cc00;
        }

        @media (max-width: 768px) {
            .hero h1 {
                font-size: 2.5rem;
            }

            .hero p {
                font-size: 1.2rem;
            }

            nav ul {
                gap: 1rem;
            }

            nav a {
                font-size: 0.9rem;
            }

            .cta-buttons {
                flex-direction: column;
                align-items: center;
            }

            .btn {
                width: 100%;
                max-width: 250px;
            }
        }
    </style>
</head>
<body>
    <!-- Navigation -->
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/">Accueil</a></li>
            <li><a href="${pageContext.request.contextPath}/bike">Nos Motos</a></li>
            <% if (session.getAttribute("user") != null) { %>
                <li><a href="${pageContext.request.contextPath}/auth/logout">🚪 Déconnexion</a></li>
            <% } else { %>
                <li><a href="${pageContext.request.contextPath}/auth/login">🔐 Connexion</a></li>
                <li><a href="${pageContext.request.contextPath}/auth/register">✍️ S'inscrire</a></li>
            <% } %>
        </ul>
    </nav>

    <!-- Hero Section -->
    <section class="hero">
        <div class="hero-content">
            <h1>🏍️ KAWASAKI</h1>
            <p>Découvrez la collection exclusive de motos Kawasaki haute performance</p>
            <div class="cta-buttons">
                <a href="${pageContext.request.contextPath}/bike" class="btn btn-primary">Voir nos motos</a>
                <a href="#features" class="btn btn-secondary">En savoir plus</a>
            </div>
        </div>
    </section>

    <!-- Features Section -->
    <section class="features" id="features">
        <h2>Pourquoi choisir Kawasaki ?</h2>
        <div class="features-grid">
            <div class="feature-card">
                <h3>⚡ Performance</h3>
                <p>Moteurs surpuissants conçus pour la vitesse et la puissance extrême</p>
            </div>
            <div class="feature-card">
                <h3>🎯 Innovation</h3>
                <p>Technologie de pointe et design révolutionnaire pour chaque modèle</p>
            </div>
            <div class="feature-card">
                <h3>👑 Légende</h3>
                <p>Plus de 50 ans d'excellence et de tradition motocycliste</p>
            </div>
        </div>
    </section>

    <!-- Stats Section -->
    <section class="stats">
        <div class="stats-grid">
            <div class="stat-item">
                <h3>10+</h3>
                <p>Modèles disponibles</p>
            </div>
            <div class="stat-item">
                <h3>300+</h3>
                <p>Chevaux max en puissance</p>
            </div>
            <div class="stat-item">
                <h3>100%</h3>
                <p>Satisfaction Kawasaki</p>
            </div>
        </div>
    </section>

    <!-- Footer -->
    <footer>
        <p>&copy; 2026 KAWASAKI. Tous droits réservés. 🏍️</p>
    </footer>
</body>
</html>