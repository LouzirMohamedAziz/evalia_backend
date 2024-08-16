@echo off
docker-compose down
docker volume rm -f docker_evalia_database
@pause