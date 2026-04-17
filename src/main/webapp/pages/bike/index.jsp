<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Collection Kawasaki - Nos Motos</title>
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

        .header p {
            font-size: 1.2rem;
            opacity: 0.9;
            color: #00cc00;
        }

        /* Main Container */
        .container {
            max-width: 1200px;
            margin: 3rem auto;
            padding: 0 2rem;
        }

        /* Bikes Grid */
        .bikes-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 2rem;
            margin-bottom: 3rem;
        }

        .bike-card {
            background: white;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            transition: all 0.3s ease;
        }

        .bike-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.2);
        }

        .bike-image {
            width: 100%;
            height: 250px;
            object-fit: cover;
            display: block;
        }

        .bike-header {
            background: linear-gradient(135deg, #000000 0%, #1a1a1a 50%, #00cc00 100%);
            color: white;
            padding: 1.5rem;
            text-align: center;
        }

        .bike-header h3 {
            font-size: 1.5rem;
            margin-bottom: 0.3rem;
        }

        .bike-header p {
            opacity: 0.9;
            font-size: 0.9rem;
        }

        .bike-body {
            padding: 1.5rem;
        }

        .bike-info {
            margin: 1rem 0;
            padding: 0.8rem;
            background: #f5f5f5;
            border-left: 4px solid #00cc00;
            border-radius: 5px;
        }

        .bike-info label {
            font-weight: 600;
            color: #1a1a1a;
            display: block;
            margin-bottom: 0.3rem;
        }

        .bike-info value {
            color: #666;
            font-size: 1.1rem;
        }

        .bike-footer {
            padding: 1.5rem;
            text-align: center;
            border-top: 1px solid #eee;
        }

        .btn {
            display: inline-block;
            padding: 0.7rem 1.5rem;
            background: #00cc00;
            color: white;
            text-decoration: none;
            border-radius: 50px;
            font-weight: 600;
            transition: all 0.3s;
            border: none;
            cursor: pointer;
        }

        .btn:hover {
            background: #00cc00;
            transform: scale(1.05);
        }

        .btn-back {
            background: #2d2d2d;
            margin-top: 2rem;
        }

        .btn-back:hover {
            background: #1a1a1a;
        }

        /* Footer */
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

            .bikes-grid {
                grid-template-columns: 1fr;
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
        <h1>🏍️ Collection Kawasaki</h1>
        <p>Explorez nos ${bikes.size()} motos Kawasaki performance</p>
    </div>

    <a href="/bike/create" class="btn" style="margin: 0.5rem;">Ajouter une moto</a>

    <!-- Bikes Grid -->
    <div class="container">
        <div class="bikes-grid">
            <c:forEach items="${bikes}" var="bike">
                <div class="bike-card">
                    <img src="${bike.imageUrl}" alt="${bike.brand} ${bike.model}" class="bike-image">
                    <div class="bike-header">
                        <h3>${bike.brand}</h3>
                        <p>${bike.model}</p>
                    </div>
                    <div class="bike-body">
                        <div class="bike-info">
                            <label>Chevaux</label>
                            <value>${bike.horsePower} ch</value>
                        </div>
                    </div>
                    <div class="bike-footer">
                        <a href="${pageContext.request.contextPath}/bike/details?id=${bike.id}" class="btn" style="display: block; margin-bottom: 0.5rem;">👁️ Voir détails</a>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div style="text-align: center;">
            <a href="${pageContext.request.contextPath}/" class="btn btn-back">← Retour à l'accueil</a>
        </div>
    </div>

    <!-- Footer -->
    <footer>
        <p>&copy; 2026 KAWASAKI. Tous droits réservés. 🏍️</p>
    </footer>
</body>
</html>

