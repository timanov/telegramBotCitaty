cd ~
git config --global user.email "mihailtimanov@gmail.com"
git config --global
git clone https://github.com/timanov/telegramBotCitatyVadimZeland.git
apt-get update
apt install openjdk-8-jdk -y
apt install maven -y
cd telegramBotCitatyVadimZeland
mvn package
sh script_sh/install_docker.sh
sh script_sh/run_docker_app.sh