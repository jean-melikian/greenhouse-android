# greenhouse-android

L'application utilise le service FCM de Google pour recevoir des notifications provenant de notre webservice.

Pour pouvoir compiler le projet, vous aurez besoin du fichier __`google-services.json`__ que nous pouvons vous fournir par email.

Il vous faudra le placer dans le sous-module __`app`__.

Nous avons mis en place deux flavors pour l'application:
- dev: pointe sur l'API de développement
- prod: pointe sur l'API de production

