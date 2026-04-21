<%@ page import="be.bstorm.introjakartaee.models.User" %>
<%@ page import="be.bstorm.introjakartaee.models.enums.UserRole" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${bike.brand} ${bike.model} - Kawasaki</title>
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
            background: linear-gradient(135deg, #1a1a1a 0%, #2d2d2d 100%);
            padding: 1rem 0;
            position: sticky;
            top: 0;
            z-index: 100;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.3);
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

        /* Header */
        .header {
            background: linear-gradient(135deg, #000000 0%, #1a1a1a 100%);
            color: white;
            padding: 3rem 2rem;
            text-align: center;
            border-bottom: 3px solid #00cc00;
        }

        .header h1 {
            font-size: 2.5rem;
            margin-bottom: 0.5rem;
        }

        /* Main Container */
        .container {
            max-width: 800px;
            margin: 3rem auto;
            padding: 0 2rem;
        }

        /* Details Card */
        .details-card {
            background: white;
            border-radius: 15px;
            overflow: hidden;
            box-shadow: 0 8px 30px rgba(0, 0, 0, 0.15);
        }

        .card-header {
            background: linear-gradient(135deg, #000000 0%, #1a1a1a 50%, #00cc00 100%);
            color: white;
            padding: 2rem;
            text-align: center;
        }

        .card-header h2 {
            font-size: 2rem;
            margin-bottom: 0.5rem;
        }

        .card-header p {
            opacity: 0.95;
            font-size: 1.1rem;
        }

        .card-body {
            padding: 2.5rem;
        }

        .detail-item {
            margin: 2rem 0;
            padding: 1.5rem;
            background: #f5f5f5;
            border-left: 5px solid #00cc00;
            border-radius: 8px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .detail-label {
            font-weight: 700;
            color: #1a1a1a;
            font-size: 1.1rem;
        }

        .detail-value {
            color: #00cc00;
            font-size: 1.3rem;
            font-weight: 600;
        }

        .actions {
            display: flex;
            gap: 1rem;
            margin-top: 2rem;
            justify-content: center;
        }

        .btn {
            padding: 1rem 2rem;
            border: none;
            border-radius: 50px;
            font-size: 1rem;
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
            color: white;
            box-shadow: 0 4px 15px rgba(255, 107, 53, 0.4);
        }

        .btn-primary:hover {
            background: #00cc00;
            transform: translateY(-3px);
            box-shadow: 0 6px 20px rgba(0, 204, 0, 0.6);
        }

        .btn-secondary {
            background: #2d2d2d;
            color: white;
            box-shadow: 0 4px 15px rgba(45, 45, 45, 0.3);
        }

        .btn-secondary:hover {
            background: #1a1a1a;
            transform: translateY(-3px);
            box-shadow: 0 6px 20px rgba(45, 45, 45, 0.5);
        }

        .bike-image {
            width: 100%;
            height: 500px;
            object-fit: cover;
            display: block;
        }

        /* Footer */
        footer {
            background: #000000;
            color: white;
            text-align: center;
            padding: 2rem;
            margin-top: 4rem;
            border-top: 3px solid #00cc00;
        }

        @media (max-width: 768px) {
            .header h1 {
                font-size: 2rem;
            }

            .detail-item {
                flex-direction: column;
                align-items: flex-start;
            }

            .detail-value {
                margin-top: 0.5rem;
            }

            .actions {
                flex-direction: column;
            }

            .btn {
                width: 100%;
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
            <li><a href="${pageContext.request.contextPath}/">Accueil</a></li>
            <li><a href="${pageContext.request.contextPath}/bike">Nos Motos</a></li>
        </ul>
    </nav>

    <!-- Header -->
    <div class="header">
        <h1>🏍️ Détails Kawasaki</h1>
    </div>

    <!-- Details Container -->
    <div class="container">
        <div class="details-card">
            <img src="${bike.imageUrl}" alt="${bike.brand} ${bike.model}" class="bike-image">
            <div class="card-header">
                <h2>${bike.brand}</h2>
                <p>${bike.model}</p>
            </div>
            <div class="card-body">
                <div class="detail-item">
                    <span class="detail-label">Marque</span>
                    <span class="detail-value">${bike.brand}</span>
                </div>
                <div class="detail-item">
                    <span class="detail-label">Modèle</span>
                    <span class="detail-value">${bike.model}</span>
                </div>
                <div class="detail-item">
                    <span class="detail-label">Puissance</span>
                    <span class="detail-value">${bike.horsePower} ch</span>
                </div>
                <% if (session.getAttribute("user") != null && ((User) session.getAttribute("user")).getRole() == UserRole.ADMIN) { %>
                    <div class="actions">
                        <a href="${pageContext.request.contextPath}/bike/update?id=${bike.id}" class="btn btn-warning">✏️ Modifier</a>
                        <form method="POST" action="${pageContext.request.contextPath}/bike/delete" style="flex: 1;">
                            <input type="hidden" name="id" value="${bike.id}">
                            <button type="submit" class="btn btn-danger" style="width: 100%;">🗑️ Supprimer</button>
                        </form>
                    </div>
                <% } %>

                <div class="actions">
                    <a href="${pageContext.request.contextPath}/bike" class="btn btn-secondary">← Retour à la liste</a>
                    <a href="${pageContext.request.contextPath}/" class="btn btn-primary">Accueil</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer>
        <p>&copy; 2026 KAWASAKI. Tous droits réservés. 🏍️</p>
    </footer>
</body>
</html>

