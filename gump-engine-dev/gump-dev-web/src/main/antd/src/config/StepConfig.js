export const INPUT_STEP_TYPE = 'input';

export const OUTPUT_STEP_TYPE = 'output';

export const FOR_STEP_TYPE = 'for';

export const IF_STEP_TYPE = 'if';

export const RPC_STEP_TYPE = 'callRpc';


let STEP_CONFIG = {};

STEP_CONFIG[INPUT_STEP_TYPE] = {
    startStep: true,
    stepShape: 'ellipse'
}
STEP_CONFIG[OUTPUT_STEP_TYPE] = {
    endStep: true,
    stepShape: 'ellipse'
}
STEP_CONFIG[FOR_STEP_TYPE] = {
    byRoad: true,
    stepShape: 'circle'
}
STEP_CONFIG[IF_STEP_TYPE] = {
    byRoad: true,
    defaultByRoadNextStepType: OUTPUT_STEP_TYPE,
    stepShape: 'polygon'
}
STEP_CONFIG[RPC_STEP_TYPE] = {
    byRoad: false,
    stepShape: 'rect'
}


export default STEP_CONFIG;