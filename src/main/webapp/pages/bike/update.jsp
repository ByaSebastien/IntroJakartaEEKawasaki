<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifier ${bike.brand} ${bike.model} - Kawasaki</title>
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
            background: #f5f5f5;
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
            max-width: 600px;
            margin: 3rem auto;
            padding: 0 2rem;
        }

        /* Form Card */
        .form-card {
            background: white;
            border-radius: 15px;
            overflow: hidden;
            box-shadow: 0 8px 30px rgba(0, 0, 0, 0.15);
            padding: 2rem;
        }

        .form-card h2 {
            color: #1a1a1a;
            margin-bottom: 2rem;
            text-align: center;
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        label {
            display: block;
            margin-bottom: 0.5rem;
            font-weight: 600;
            color: #1a1a1a;
        }

        input[type="text"],
        input[type="number"],
        textarea {
            width: 100%;
            padding: 0.8rem;
            border: 2px solid #eee;
            border-radius: 8px;
            font-size: 1rem;
            font-family: inherit;
            transition: border-color 0.3s;
        }

        input[type="text"]:focus,
        input[type="number"]:focus,
        textarea:focus {
            outline: none;
            border-color: #00cc00;
            box-shadow: 0 0 5px rgba(0, 204, 0, 0.3);
        }

        textarea {
            resize: vertical;
            min-height: 100px;
        }

        .form-actions {
            display: flex;
            gap: 1rem;
            margin-top: 2rem;
        }

        button,
        .btn {
            flex: 1;
            padding: 1rem;
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
            text-align: center;
        }

        .btn-primary {
            background: #00cc00;
            color: white;
        }

        .btn-primary:hover {
            background: #00aa00;
            transform: translateY(-3px);
            box-shadow: 0 6px 20px rgba(0, 204, 0, 0.6);
        }

        .btn-secondary {
            background: #2d2d2d;
            color: white;
        }

        .btn-secondary:hover {
            background: #1a1a1a;
            transform: translateY(-3px);
            box-shadow: 0 6px 20px rgba(45, 45, 45, 0.5);
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

            nav ul {
                gap: 1rem;
            }

            nav a {
                font-size: 0.9rem;
            }

            .form-actions {
                flex-direction: column;
            }

            button,
            .btn {
                width: 100%;
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
        <h1>🏍️ Modifier une Moto</h1>
    </div>

    <!-- Form Container -->
    <div class="container">
        <div class="form-card">
            <h2>Éditer ${bike.brand} ${bike.model}</h2>
            <form method="POST" action="${pageContext.request.contextPath}/bike/update">
                <input type="hidden" name="id" value="${bike.id}">

                <div class="form-group">
                    <label for="brand">Marque</label>
                    <input type="text" id="brand" name="brand" value="${bike.brand}" required>
                </div>

                <div class="form-group">
                    <label for="model">Modèle</label>
                    <input type="text" id="model" name="model" value="${bike.model}" required>
                </div>

                <div class="form-group">
                    <label for="horsePower">Puissance (ch)</label>
                    <input type="number" id="horsePower" name="horsePower" value="${bike.horsePower}" required>
                </div>

                <div class="form-group">
                    <label for="imageUrl">URL de l'image</label>
                    <textarea id="imageUrl" name="imageUrl" required>${bike.imageUrl}</textarea>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Mettre à jour</button>
                    <a href="${pageContext.request.contextPath}/bike/details?id=${bike.id}" class="btn btn-secondary">Annuler</a>
                </div>
            </form>
        </div>
    </div>

    <!-- Footer -->
    <footer>
        <p>&copy; 2026 KAWASAKI. Tous droits réservés. 🏍️</p>
    </footer>
</body>
</html>

