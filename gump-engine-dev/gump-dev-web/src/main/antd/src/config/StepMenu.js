import * as StepConfig from './StepConfig';

export default [
    {
        key: 'basicStep',
        label: '基础环节',
        children: [
            {
                key: StepConfig.FOR_STEP_TYPE,
                label: 'For循环',
            },
            {
                key: StepConfig.IF_STEP_TYPE,
                label: '逻辑判断',
            }
        ],
    },
    {
        key: 'thirdMiddleware',
        label: '三方中间件环节',
        children: [
            {
                key: StepConfig.RPC_STEP_TYPE,
                label: 'RPC调用',
            }
        ],
    }
]