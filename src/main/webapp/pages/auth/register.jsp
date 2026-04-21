<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inscription - Kawasaki</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            line-height: 1.6;
            background: linear-gradient(135deg, #000000 0%, #1a1a1a 100%);
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        /* Navigation */
        nav {
            background: linear-gradient(135deg, #000000 0%, #1a1a1a 100%);
            padding: 1rem 0;
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

        /* Main Container */
        .container {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 2rem;
        }

        /* Form Card */
        .form-card {
            background: white;
            border-radius: 15px;
            box-shadow: 0 10px 40px rgba(0, 204, 0, 0.2);
            width: 100%;
            max-width: 450px;
            padding: 3rem 2rem;
            position: relative;
            overflow: hidden;
        }

        .form-card::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            height: 4px;
            background: linear-gradient(90deg, #000000 0%, #00cc00 100%);
        }

        .form-card h1 {
            text-align: center;
            color: #1a1a1a;
            font-size: 2rem;
            margin-bottom: 0.5rem;
            font-weight: 900;
        }

        .form-card .subtitle {
            text-align: center;
            color: #00cc00;
            margin-bottom: 2rem;
            font-size: 0.9rem;
            font-weight: 600;
            letter-spacing: 1px;
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        .form-group label {
            display: block;
            color: #1a1a1a;
            font-weight: 600;
            margin-bottom: 0.5rem;
            text-transform: uppercase;
            font-size: 0.85rem;
            letter-spacing: 0.5px;
        }

        .form-group input {
            width: 100%;
            padding: 0.8rem 1rem;
            border: 2px solid #e0e0e0;
            border-radius: 8px;
            font-size: 1rem;
            transition: all 0.3s;
            font-family: inherit;
        }

        .form-group input:focus {
            outline: none;
            border-color: #00cc00;
            box-shadow: 0 0 0 3px rgba(0, 204, 0, 0.1);
        }

        .form-group input::placeholder {
            color: #999;
        }

        .btn {
            width: 100%;
            padding: 0.9rem;
            background: linear-gradient(135deg, #00cc00 0%, #00aa00 100%);
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 1rem;
            font-weight: 700;
            text-transform: uppercase;
            letter-spacing: 1px;
            cursor: pointer;
            transition: all 0.3s;
            margin-top: 1rem;
            box-shadow: 0 4px 15px rgba(0, 204, 0, 0.3);
        }

        .btn:hover {
            background: linear-gradient(135deg, #00aa00 0%, #008800 100%);
            transform: translateY(-2px);
            box-shadow: 0 6px 20px rgba(0, 204, 0, 0.5);
        }

        .form-footer {
            text-align: center;
            margin-top: 2rem;
            padding-top: 1.5rem;
            border-top: 1px solid #e0e0e0;
        }

        .form-footer p {
            color: #666;
            font-size: 0.9rem;
        }

        .form-footer a {
            color: #00cc00;
            text-decoration: none;
            font-weight: 600;
            transition: color 0.3s;
        }

        .form-footer a:hover {
            color: #00aa00;
            text-decoration: underline;
        }

        .info-box {
            background: #f5f5f5;
            border-left: 4px solid #00cc00;
            padding: 1rem;
            border-radius: 5px;
            margin-bottom: 1.5rem;
            font-size: 0.85rem;
            color: #666;
        }

        .error-message {
            background: #ffebee;
            border-left: 4px solid #d32f2f;
            color: #c62828;
            padding: 1rem;
            border-radius: 5px;
            margin-bottom: 1.5rem;
            font-size: 0.9rem;
            font-weight: 500;
        }

        /* Footer */
        footer {
            background: #000000;
            color: white;
            text-align: center;
            padding: 2rem;
            border-top: 3px solid #00cc00;
        }

        @media (max-width: 600px) {
            .form-card {
                padding: 2rem 1.5rem;
                border-radius: 10px;
            }

            .form-card h1 {
                font-size: 1.5rem;
            }

            nav ul {
                gap: 1rem;
            }

            nav a {
                font-size: 0.9rem;
            }
        }
    </style>
</head>
<body>
    <!-- Navigation -->
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/">🏠 Accueil</a></li>
            <li><a href="${pageContext.request.contextPath}/bike">🏍️ Nos Motos</a></li>
        </ul>
    </nav>

    <!-- Main Container -->
    <div class="container">
        <div class="form-card">
            <h1>🚀 INSCRIPTION</h1>
            <p class="subtitle">Rejoins la communauté Kawasaki</p>

            <% if (request.getAttribute("error") != null) { %>
                <div class="error-message">
                    ❌ <%= request.getAttribute("error") %>
                </div>
            <% } %>

            <% if (request.getAttribute("success") != null) { %>
                <div class="info-box" style="background: #e8f5e9; border-left-color: #2e7d32; color: #1b5e20;">
                    ✅ <%= request.getAttribute("success") %>
                </div>
            <% } %>

            <div class="info-box">
                ⚡ Créez votre compte pour accéder à la collection complète de motos Kawasaki
            </div>

            <form method="post" action="${pageContext.request.contextPath}/auth/register">
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" placeholder="votre@email.com" required>
                </div>

                <div class="form-group">
                    <label for="password">Mot de passe</label>
                    <input type="password" id="password" name="password" placeholder="••••••••" required minlength="6">
                </div>

                <button type="submit" class="btn">✨ S'inscrire</button>
            </form>

            <div class="form-footer">
                <p>Déjà membre ? <a href="${pageContext.request.contextPath}/auth/login">Se connecter</a></p>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer>
        <p>&copy; 2026 KAWASAKI. Tous droits réservés. 🏍️</p>
    </footer>
</body>
</html>
