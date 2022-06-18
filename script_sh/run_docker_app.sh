cd rest_example

sudo docker build -t telegramBotZeland:1.0 .
sudo docker run -d -p 8030:8030 -t telegramBotZeland:1.0