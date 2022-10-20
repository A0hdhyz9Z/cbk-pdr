<template>
    <div class="l-captcha" data-site-key="e11777fac0ab293290c12b91484c7a4f" data-callback="getResponse"
        data-width="100%"></div>
</template>
<script>
export default {
    name: 'validateCaptcha',
    methods: {
        dynamicLoadJs: function (url, callback) {
            var head = document.getElementsByTagName('head')[0]
            var script = document.createElement('script')
            script.type = 'text/javascript'
            script.src = url
            if (typeof callback === 'function') {
                script.onload = script.onreadystatechange = function () {
                    if (
                        !this.readyState ||
                        this.readyState === 'loaded' ||
                        this.readyState === 'complete'
                    ) {
                        callback()
                        script.onload = script.onreadystatechange = null
                    }
                }
            }
            head.appendChild(script)
        }
    },
    created() {
        const self = this
        this.dynamicLoadJs('//captcha.luosimao.com/static/dist/api.js')
        window.getResponse = (resp) => {
            var els = document.getElementsByName('luotest_response')
            if (els.length === 1 && els[0].value === resp) {
                self.$emit('getValidateRes', resp)
            } else {
                LUOCAPTCHA && LUOCAPTCHA.reset()
            }
        }
    }
}
</script>