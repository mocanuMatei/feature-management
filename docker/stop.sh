echo "Stopping docker containers ..."
docker-compose down

echo "Deleting containers ..."
docker system prune -f