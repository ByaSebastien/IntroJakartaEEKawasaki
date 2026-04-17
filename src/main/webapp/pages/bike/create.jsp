<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter une moto - Kawasaki</title>
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
            flex-wrap: wrap;
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

        .container {
            max-width: 700px;
            margin: 3rem auto;
            padding: 0 2rem;
        }

        .form-card {
            background: white;
            border-radius: 10px;
            padding: 2rem;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            border-top: 4px solid #00cc00;
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        .form-group label {
            display: block;
            margin-bottom: 0.5rem;
            color: #1a1a1a;
            font-weight: 600;
        }

        .form-group input,
        .form-group textarea {
            width: 100%;
            padding: 0.8rem;
            border: 2px solid #eee;
            border-radius: 5px;
            font-size: 1rem;
            font-family: inherit;
            transition: border-color 0.3s;
        }

        .form-group input:focus,
        .form-group textarea:focus {
            outline: none;
            border-color: #00cc00;
        }

        .form-group textarea {
            resize: vertical;
            min-height: 100px;
        }

        .form-row {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 1rem;
        }

        .error-message {
            background: #ff6b6b;
            color: white;
            padding: 1rem;
            border-radius: 5px;
            margin-bottom: 1.5rem;
            border-left: 4px solid #ff0000;
        }

        .form-actions {
            display: flex;
            gap: 1rem;
            margin-top: 2rem;
        }

        .btn {
            padding: 1rem;
            border: none;
            border-radius: 5px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s;
            text-decoration: none;
            display: inline-block;
            flex: 1;
            text-align: center;
            font-size: 1rem;
        }

        .btn-primary {
            background: #00cc00;
            color: black;
        }

        .btn-primary:hover {
            background: #00aa00;
            transform: translateY(-2px);
        }

        .btn-secondary {
            background: #2d2d2d;
            color: white;
        }

        .btn-secondary:hover {
            background: #1a1a1a;
        }

        footer {
            background: #1a1a1a;
            color: white;
            text-align: center;
            padding: 2rem;
            margin-top: 4rem;
        }

        @media (max-width: 768px) {
            .header h1 {
                font-size: 2rem;
            }

            .form-row {
                grid-template-columns: 1fr;
            }

            .form-actions {
                flex-direction: column;
            }

            .btn {
                width: 100%;
            }
        }
    </style>
</head>
<body>
<nav>
    <ul>
        <li><a href="${pageContext.request.contextPath}/">Accueil</a></li>
        <li><a href="${pageContext.request.contextPath}/bike">Nos Motos</a></li>
    </ul>
</nav>

<div class="header">
    <h1>🏍️ Ajouter une nouvelle moto</h1>
    <p>Enrichissez le catalogue Kawasaki</p>
</div>

<div class="container">
    <div class="form-card">
        <c:if test="${not empty errorMessage}">
            <div class="error-message">
                <strong>Erreur:</strong> ${errorMessage}
            </div>
        </c:if>

        <form method="POST" action="${pageContext.request.contextPath}/bike/create">
            <div class="form-group">
                <label for="brand">Marque *</label>
                <input type="text" id="brand" name="brand" value="${brand}" required placeholder="Ex: Kawasaki">
            </div>

            <div class="form-group">
                <label for="model">Modèle *</label>
                <input type="text" id="model" name="model" value="${model}" required placeholder="Ex: Ninja H2">
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="horsePower">Chevaux *</label>
                    <input type="number" id="horsePower" name="horsePower" required min="1" placeholder="Ex: 310">
                </div>
            </div>

            <div class="form-group">
                <label for="imageUrl">URL de l'image</label>
                <input type="url" id="imageUrl" name="imageUrl" value="${imageUrl}" placeholder="https://example.com/image.jpg">
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Créer la moto</button>
                <a href="${pageContext.request.contextPath}/bike" class="btn btn-secondary">Annuler</a>
            </div>
        </form>
    </div>
</div>

<footer>
    <p>&copy; 2026 KAWASAKI. Tous droits réservés. 🏍️</p>
</footer>
</body>
</html>

