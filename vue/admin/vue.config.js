"use strict";

module.exports = {
  publicPath: "./",
  productionSourceMap: false,
  devServer: {
    open: true,
    host: "localhost",
    port: 8001,
    https: false,
    hotOnly: false,

    proxy: {
      "/api": {
        target: "http://localhost:8000",
        changeOrigin: true
      }
    }
  },
  configureWebpack: config => {
    // 开发环境配置
    if (process.env.NODE_ENV === "development") {
      config.devtool = "source-map";
    }
  }
};
