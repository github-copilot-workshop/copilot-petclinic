ARG VARIANT=17-bullseye
FROM mcr.microsoft.com/vscode/devcontainers/java:0-${VARIANT}

ARG NODE_VERSION="none"
RUN if [ "${NODE_VERSION}" != "none" ]; then su vscode -c "umask 0002 && . /usr/local/share/nvm/nvm.sh && nvm install ${NODE_VERSION} 2>&1"; fi

RUN sudo mkdir -p -m 755 /etc/apt/keyrings && \
    wget -qO- https://cli.github.com/packages/githubcli-archive-keyring.gpg | sudo tee /etc/apt/keyrings/githubcli-archive-keyring.gpg > /dev/null \
    && sudo chmod go+r /etc/apt/keyrings/githubcli-archive-keyring.gpg \
    && echo "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/githubcli-archive-keyring.gpg] https://cli.github.com/packages stable main" | sudo tee /etc/apt/sources.list.d/github-cli.list > /dev/null \
    && sudo apt update \
    && sudo apt install bash-completion gh -y \
    && sudo rm -rf /var/lib/{apt,dpkg,cache,log}/ \
    && sudo touch /etc/bash_completion.d/gh \
    && sudo chmod a+x /etc/bash_completion.d/gh \
    && sudo gh completion -s bash | sudo tee /etc/bash_completion.d/gh

ARG USER=vscode
VOLUME /home/$USER/.m2
VOLUME /home/$USER/.gradle

ARG JAVA_VERSION=17.0.7-ms
RUN sudo mkdir /home/$USER/.m2 /home/$USER/.gradle \
    && sudo chown $USER:$USER /home/$USER/.m2 /home/$USER/.gradle \
    && echo "source /etc/bash_completion.d/gh" | tee -a /home/$USER/.bashrc
RUN bash -lc '. /usr/local/sdkman/bin/sdkman-init.sh && sdk install java $JAVA_VERSION && sdk use java $JAVA_VERSION'
